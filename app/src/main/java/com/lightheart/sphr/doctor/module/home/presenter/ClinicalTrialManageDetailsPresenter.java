package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.ClinicalTrialManageDetails;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalTrialManageDetailsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


public class ClinicalTrialManageDetailsPresenter extends BasePresenter<ClinicalTrialManageDetailsContract.View> implements ClinicalTrialManageDetailsContract.Presenter {

    @Inject
    ClinicalTrialManageDetailsPresenter() {

    }
    @Override
    public void loadClinicalDetailsData(int id) {
        DetailsBean entity = new DetailsBean();
        entity.duid= SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        entity.id=id;

        RetrofitManager.create(ApiService.class)
                .detailslist(entity)
                .compose(RxSchedulers.<DataResponse<ClinicalTrialManageDetails>>applySchedulers())
                .compose(mView.<DataResponse<ClinicalTrialManageDetails>>bindToLife())
                .subscribe(new Consumer<DataResponse<ClinicalTrialManageDetails>>() {
                   @Override
                   public void accept(DataResponse<ClinicalTrialManageDetails> response) throws Exception {

                       if(response.getResultcode()==200) {
                           mView.setClinicalDetailsData(response.getContent());
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


}
