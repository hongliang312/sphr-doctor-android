package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.HomePanelModel;
import com.lightheart.sphr.doctor.bean.PanelRequestParams;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.bean.ShareClinical2PanelParam;
import com.lightheart.sphr.doctor.module.home.contract.HomePanelContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-23.
 * Description :
 */

public class PanelsPresenter extends BasePresenter<HomePanelContract.View> implements HomePanelContract.Presenter {

    private PanelRequestParams params = new PanelRequestParams();
    private boolean mIsRefresh;

    @Inject
    public PanelsPresenter() {
        this.mIsRefresh = true;
        params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    }

    @Override
    public void loadPanelList() {
        RetrofitManager.create(ApiService.class)
                .getDtmAroList(params)
                .compose(RxSchedulers.<DataResponse<HomePanelModel>>applySchedulers())
                .compose(mView.<DataResponse<HomePanelModel>>bindToLife())
                .subscribe(new Consumer<DataResponse<HomePanelModel>>() {
                    @Override
                    public void accept(DataResponse<HomePanelModel> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setPanelData(response.getContent(), loadType);
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadPanelAllList(String isMember) {
        params.isMember = isMember;
        RetrofitManager.create(ApiService.class)
                .getAllDtmAroList(params)
                .compose(RxSchedulers.<DataResponse<List<PanelsModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<PanelsModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<PanelsModel>>>() {
                    @Override
                    public void accept(DataResponse<List<PanelsModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setPanelList(response.getContent(), loadType);
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void refresh() {
        mIsRefresh = true;
        loadPanelList();
    }

    @Override
    public void share2Panel(ShareClinical2PanelParam param) {
        RetrofitManager.create(ApiService.class)
                .share2Panel(param)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.successShare();
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }
}
