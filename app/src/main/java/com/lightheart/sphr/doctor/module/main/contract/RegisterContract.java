package com.lightheart.sphr.doctor.module.main.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.User;

/**
 * Created by fucp on 2018-4-16.
 * Description :
 */

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void sendCodeSucess();

        void registerSuccess(User user);

        void verCodeSuccess(User user);
    }

    interface Presenter extends BaseContract.BasePresenter<RegisterContract.View> {

        void sendAuthCode(LoginRequest parmas);

        void verifyAuthCode(LoginRequest.Data data, String password, String mFlag);

        void register(LoginRequest parmas);

        void modifyPsd(LoginRequest parmas);

        void verCodeLogin(LoginRequest parmas);

    }

}
