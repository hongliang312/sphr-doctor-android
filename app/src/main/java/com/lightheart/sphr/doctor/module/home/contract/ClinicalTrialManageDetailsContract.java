package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ClinicalTrialManageDetails;


public interface ClinicalTrialManageDetailsContract {
    interface View extends BaseContract.BaseView{

        void setClinicalDetailsData(ClinicalTrialManageDetails content);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadClinicalDetailsData(int id);


    }
}
