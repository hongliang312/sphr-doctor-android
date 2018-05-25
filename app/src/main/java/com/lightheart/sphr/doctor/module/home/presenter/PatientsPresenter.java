package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.PatientsModel;
import com.lightheart.sphr.doctor.bean.PatientsRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.PatientManageContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-23.
 * Description :
 */

public class PatientsPresenter extends BasePresenter<PatientManageContract.View> implements PatientManageContract.Presenter {

    private PatientsRequestParams params = new PatientsRequestParams();
    private boolean mIsRefresh;

    @Inject
    public PatientsPresenter() {
        this.mIsRefresh = true;
        params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        params.pageSize = 100;
    }

    @Override
    public void loadPatientData(int pageNum, int timeCategory) {
        params.timeCategory = timeCategory;
        params.pageNum = pageNum;
        RetrofitManager.create(ApiService.class)
                .getPatientByDuid(params)
                .compose(RxSchedulers.<DataResponse<PatientsModel>>applySchedulers())
                .compose(mView.<DataResponse<PatientsModel>>bindToLife())
                .subscribe(new Consumer<DataResponse<PatientsModel>>() {
                    @Override
                    public void accept(DataResponse<PatientsModel> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setPatients(response.getContent().list, loadType);
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
    public void refresh(int pageNum, int timeCategory) {
        pageNum = 1;
        mIsRefresh = true;
        loadPatientData(pageNum, timeCategory);
    }

    @Override
    public void loadMore(int pageNum, int timeCategory) {
        mIsRefresh = false;
        loadPatientData(pageNum, timeCategory);
    }


}
