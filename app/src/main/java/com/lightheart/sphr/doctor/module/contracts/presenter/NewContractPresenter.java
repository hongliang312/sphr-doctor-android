package com.lightheart.sphr.doctor.module.contracts.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.module.contracts.contract.NewContractsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-9.
 * Description :
 */

public class NewContractPresenter extends BasePresenter<NewContractsContract.View> implements NewContractsContract.Presenter {

    private DocContractRequestParams params = new DocContractRequestParams();
    private boolean mIsRefresh;
    private int mPage = 1;

    @Inject
    public NewContractPresenter() {
        this.mIsRefresh = true;
        this.params.status = "APL";
        this.params.pageSize = 30;
        this.params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        this.params.pageNum = mPage;
    }

    @Override
    public void loadNewContractData() {
        RetrofitManager.create(ApiService.class)
                .getContractList(params)
                .compose(RxSchedulers.<DataResponse<List<ContractDocItem>>>applySchedulers())
                .compose(mView.<DataResponse<List<ContractDocItem>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<ContractDocItem>>>() {
                    @Override
                    public void accept(DataResponse<List<ContractDocItem>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setNewContracts(response.getContent(), loadType);
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
    public void operateDoc(DocContractRequestParams params) {
        RetrofitManager.create(ApiService.class)
                .docOperate(params)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.showSuccess((String) response.getContent());
                            loadNewContractData();
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
        loadNewContractData();
    }

    @Override
    public void loadMore() {
        mPage++;
        this.params.pageNum = mPage;
        mIsRefresh = false;
        loadNewContractData();
    }

}
