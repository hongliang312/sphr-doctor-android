package com.lightheart.sphr.doctor.module.main.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.LoginRequest;

/**
 * Created by fucp on 2018-4-16.
 * Description :
 */

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void sendCodeSucess();

        void registerSuccess(DoctorBean user);

        void verCodeSuccess(DoctorBean user);
    }

    interface Presenter extends BaseContract.BasePresenter<RegisterContract.View> {

        void sendAuthCode(LoginRequest parmas);

        void verifyAuthCode(LoginRequest.Data data, String password, String mFlag);

        void register(LoginRequest parmas);

        void modifyPsd(LoginRequest parmas);

        void verCodeLogin(LoginRequest parmas);

    }

}
