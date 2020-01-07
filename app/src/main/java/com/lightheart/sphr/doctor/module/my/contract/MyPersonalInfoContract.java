package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorModel;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.bean.TitlesModel;

import java.io.File;
import java.util.List;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface MyPersonalInfoContract {

    interface View extends BaseContract.BaseView {

        void setTitles(List<TitlesModel> titles);

        void setHospitals(List<HospitalsModel> hospitals);

        void successUploadImage(String url);

        void updateSuccess();

    }

    interface Presenter extends BaseContract.BasePresenter<MyPersonalInfoContract.View> {

        void loadTitleData();

        void loadHospitalData(int distractId);

        void uploadHeadImage(File file);

        void updatePersonalInfo(DoctorModel info);

    }

}
