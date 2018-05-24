package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;

public interface PatientRecordsContract {

    interface View extends BaseContract.BaseView {

        void setPatientRecords(PatientRecordsBean patientRecordsBean, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadPatientRecordsData(int id);

        void refresh(int id);

    }
}
