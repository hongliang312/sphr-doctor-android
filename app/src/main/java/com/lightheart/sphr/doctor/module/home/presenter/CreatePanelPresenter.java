package com.lightheart.sphr.doctor.module.home.presenter;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DiseaseModel;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.module.home.contract.CreatePanelContract;
import com.lightheart.sphr.doctor.module.home.contract.HomeContract;
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

public class CreatePanelPresenter extends BasePresenter<CreatePanelContract.View> implements CreatePanelContract.Presenter {

    @Inject
    public CreatePanelPresenter() {
    }

    @Override
    public void loadDiseaseData() {
        RetrofitManager.create(ApiService.class)
                .getDiseases(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<List<DiseaseModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<DiseaseModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<DiseaseModel>>>() {
                    @Override
                    public void accept(DataResponse<List<DiseaseModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setDiseases(response.getContent());
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
    public void createPanel() {

    }
}
