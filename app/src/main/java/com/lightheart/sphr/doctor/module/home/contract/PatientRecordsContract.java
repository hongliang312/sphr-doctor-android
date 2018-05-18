package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;

/**
 * Created by 知足 on 2018/5/16.
 */

public interface PatientRecordsContract {
    interface View extends BaseContract.BaseView{

        void setPatientRecords(PatientRecordsBean content);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadPatientRecordsData(PatientRecordsRequestParams Params);

    }
}
