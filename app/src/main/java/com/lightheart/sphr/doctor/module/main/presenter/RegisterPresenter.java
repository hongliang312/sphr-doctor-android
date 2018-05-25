package com.lightheart.sphr.doctor.module.main.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.module.main.contract.RegisterContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-16.
 * Description :
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Inject
    public RegisterPresenter() {
    }

    /**
     * 发送验证码
     *
     * @param parmas
     */
    @Override
    public void sendAuthCode(LoginRequest parmas) {
        RetrofitManager.create(ApiService.class)
                .sendAuthCode(parmas)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.sendCodeSucess();
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

    /**
     * 校验验证码
     *
     * @param data
     * @param password
     * @param mFlag
     */
    @Override
    public void verifyAuthCode(final LoginRequest.Data data, final String password, final String mFlag) {
        RetrofitManager.create(ApiService.class)
                .verifyAuthCode(data)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            LoginRequest loginRequest = new LoginRequest();
                            LoginRequest.Data data1 = new LoginRequest.Data();
                            data1.mobile = data.mobile;
                            data1.password = password;
                            loginRequest.data = new Gson().toJson(data1);
                            if (TextUtils.equals(mFlag, "REGISTER"))
                                register(loginRequest);
                            else modifyPsd(loginRequest);
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

    /**
     * 注册
     *
     * @param parmas
     */
    @Override
    public void register(LoginRequest parmas) {
        RetrofitManager.create(ApiService.class)
                .register(parmas)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.registerSuccess(response.getContent());
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

    /**
     * 修改密码
     *
     * @param parmas
     */
    @Override
    public void modifyPsd(LoginRequest parmas) {
        RetrofitManager.create(ApiService.class)
                .modifyPsd(parmas)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.registerSuccess(response.getContent());
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

    /**
     * 验证码登录
     *
     * @param parmas
     */
    @Override
    public void verCodeLogin(LoginRequest parmas) {
        RetrofitManager.create(ApiService.class)
                .authCodeLogin(parmas)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.verCodeSuccess(response.getContent());
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
