package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.bean.AuthParam;
import com.lightheart.sphr.doctor.base.BaseContract;

import java.io.File;

/**
 * Created by fucp on 2018-5-23.
 * Description :
 */

public interface AuthenticationContract {

    interface View extends BaseContract.BaseView {

        void successUploadImage(String url);

        void updateSuccess();

    }

    interface Presenter extends BaseContract.BasePresenter<AuthenticationContract.View> {

        void uploadImage(File file);

        void updatePersonalInfo(AuthParam param);

    }

}
