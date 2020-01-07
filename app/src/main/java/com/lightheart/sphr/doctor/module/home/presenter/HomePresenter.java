package com.lightheart.sphr.doctor.module.home.presenter;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.module.home.contract.HomeContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-23.
 * Description :
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private boolean mIsRefresh;

    @Inject
    public HomePresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadHomeData() {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getHomePageInfo(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<HomePageInfo>>applySchedulers())
                .compose(mView.<DataResponse<HomePageInfo>>bindToLife())
                .subscribe(new Consumer<DataResponse<HomePageInfo>>() {
                    @Override
                    public void accept(DataResponse<HomePageInfo> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setHomeBanners(response.getContent().getBannerList());
                            mView.setNotices(response.getContent().getNoticeList());
                            mView.setClinicals(response.getContent().getClinicalTrialList(), loadType);
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void refresh() {
        mIsRefresh = true;
        loadHomeData();
    }


}
