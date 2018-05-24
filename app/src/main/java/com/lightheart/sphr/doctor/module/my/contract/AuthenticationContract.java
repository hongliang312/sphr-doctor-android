package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.AuthParam;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorModel;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.bean.TitlesModel;

import java.io.File;
import java.util.List;

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
