package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ClinicalTrialManageDetails;

/**
 * Created by 知足 on 2018/5/11.
 */

public interface ClinicalTrialManageDetailsContract {
    interface View extends BaseContract.BaseView{

        void setClinicalDetalsData(ClinicalTrialManageDetails content);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadClinicalDetalsData(int id);


    }
}
