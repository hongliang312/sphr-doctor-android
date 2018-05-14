package com.lightheart.sphr.doctor.module.contracts.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.contract.SearchDoctorContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-10.
 * Description :
 */

public class SearchDoctorPresenter extends BasePresenter<SearchDoctorContract.View> implements SearchDoctorContract.Presenter {

    private DocContractRequestParams params = new DocContractRequestParams();
    private boolean mIsRefresh;
    private int mPage = 1;
    private String search;

    @Inject
    public SearchDoctorPresenter() {
        this.mIsRefresh = true;
        this.params.pageSize = 10;
        this.params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        this.params.pageNum = mPage;
    }

    @Override
    public void loadDoctors(String mobile) {
        this.search = mobile;
        this.params.mobile = mobile;
        RetrofitManager.create(ApiService.class)
                .searchDoc(params)
                .compose(RxSchedulers.<DataResponse<List<DoctorBean>>>applySchedulers())
                .compose(mView.<DataResponse<List<DoctorBean>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<DoctorBean>>>() {
                    @Override
                    public void accept(DataResponse<List<DoctorBean>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setSearchDoctors(response.getContent(), loadType);
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setSearchDoctors(new ArrayList<DoctorBean>(), loadType);
                    }
                });
    }

    @Override
    public void loadMore() {
        mPage++;
        this.params.pageNum = mPage;
        mIsRefresh = false;
        loadDoctors(search);
    }

}
