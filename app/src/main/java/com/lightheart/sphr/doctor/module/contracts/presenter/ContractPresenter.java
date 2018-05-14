package com.lightheart.sphr.doctor.module.contracts.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.contract.ContractsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-25.
 * Description :联系人adapter
 */

public class ContractPresenter extends BasePresenter<ContractsContract.View> implements ContractsContract.Presenter {

    private DocContractRequestParams params = new DocContractRequestParams();
    private boolean mIsRefresh;
    private int mPage = 1;

    @Inject
    public ContractPresenter() {
        this.mIsRefresh = true;
        this.params.status = "ADD";
        this.params.pageSize = 30;
        this.params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        this.params.pageNum = mPage;
    }

    @Override
    public void loadContractData() {
        RetrofitManager.create(ApiService.class)
                .getContractList(params)
                .compose(RxSchedulers.<DataResponse<List<DoctorBean>>>applySchedulers())
                .compose(mView.<DataResponse<List<DoctorBean>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<DoctorBean>>>() {
                    @Override
                    public void accept(DataResponse<List<DoctorBean>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setClinicals(response.getContent(), loadType);
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
    public void deleteDoc(DocContractRequestParams params) {
        RetrofitManager.create(ApiService.class)
                .docOperate(params)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.showSuccess((String) response.getContent());
                            loadContractData();
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
        this.params.pageNum = 1;
        mIsRefresh = true;
        loadContractData();
    }

    @Override
    public void loadMore() {
        mPage++;
        this.params.pageNum = mPage;
        mIsRefresh = false;
        loadContractData();
    }

}
