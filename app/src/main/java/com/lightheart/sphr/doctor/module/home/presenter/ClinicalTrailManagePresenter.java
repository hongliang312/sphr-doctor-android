package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.ClinicalTrailModel;
import com.lightheart.sphr.doctor.bean.TextsingRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalTrailManageContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class ClinicalTrailManagePresenter extends BasePresenter<ClinicalTrailManageContract.View> implements ClinicalTrailManageContract.Presenter {


    private TextsingRequestParams requestParams = new TextsingRequestParams();
    private boolean mIsRefresh;

    @Inject
    public ClinicalTrailManagePresenter() {
        this.requestParams.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        this.mIsRefresh = true;
    }

    @Override
    public void loadClinicalData() {
        RetrofitManager.create(ApiService.class)
                .Testinglist(requestParams)
                .compose(RxSchedulers.<DataResponse<List<ClinicalTrailModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<ClinicalTrailModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<ClinicalTrailModel>>>() {
                    @Override
                    public void accept(DataResponse<List<ClinicalTrailModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setClinicalData(response.getContent(), loadType);
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
        loadClinicalData();
    }
}
