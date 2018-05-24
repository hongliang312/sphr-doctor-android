package com.lightheart.sphr.doctor.module.main.presenter;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.User;
import com.lightheart.sphr.doctor.module.main.contract.LoginContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-12.
 * Description :
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(LoginRequest parmas) {
        RetrofitManager.create(ApiService.class)
                .login(parmas)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.loginSuccess(response.getContent());
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
