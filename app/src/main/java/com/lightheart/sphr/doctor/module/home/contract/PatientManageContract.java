package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.PatientsModel;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface PatientManageContract {

    interface View extends BaseContract.BaseView {

        void setPatients(List<PatientsModel.PatientModel> patientModels, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadPatientData(int pageNum, int timeCategory);

        void refresh(int pageNum, int timeCategory);

        void loadMore(int pageNum, int timeCategory);

    }

}
