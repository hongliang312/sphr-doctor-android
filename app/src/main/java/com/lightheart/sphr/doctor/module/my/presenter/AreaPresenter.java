package com.lightheart.sphr.doctor.module.my.presenter;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.AreaModel;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.PanelShareParam;
import com.lightheart.sphr.doctor.module.my.contract.AreaContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-22.
 * Description :
 */

public class AreaPresenter extends BasePresenter<AreaContract.View> implements AreaContract.Presenter {

    @Inject
    public AreaPresenter() {
    }

    @Override
    public void loadAreaData() {
        RetrofitManager.create(ApiService.class)
                .getProviceList(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<List<AreaModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<AreaModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<AreaModel>>>() {
                    @Override
                    public void accept(DataResponse<List<AreaModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setAreas(response.getContent());
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
    public void loadChildAreaData(int id) {
        PanelShareParam panelShareParam = new PanelShareParam();
        panelShareParam.id = id;
        RetrofitManager.create(ApiService.class)
                .getAreaListByFid(panelShareParam)
                .compose(RxSchedulers.<DataResponse<List<AreaModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<AreaModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<AreaModel>>>() {
                    @Override
                    public void accept(DataResponse<List<AreaModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setChildAreas(response.getContent());
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
    public void loadDepartmentData() {
        RetrofitManager.create(ApiService.class)
                .getDepartments(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<List<AreaModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<AreaModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<AreaModel>>>() {
                    @Override
                    public void accept(DataResponse<List<AreaModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setDepartment(response.getContent());
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
