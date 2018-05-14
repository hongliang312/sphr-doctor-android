package com.lightheart.sphr.doctor.module.main.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.User;

/**
 * Created by fucp on 2018-4-12.
 * Description :
 */

public interface LoginContract {

    interface View extends BaseContract.BaseView {
        void loginSuccess(User user);
    }

    interface Presenter extends BaseContract.BasePresenter<LoginContract.View> {
        void login(LoginRequest parmas);
    }

}
