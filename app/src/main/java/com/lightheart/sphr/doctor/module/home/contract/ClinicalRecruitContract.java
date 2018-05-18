package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.CreatePanelParam;
import com.lightheart.sphr.doctor.bean.DiseaseModel;
import com.lightheart.sphr.doctor.bean.HomePageInfo;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface ClinicalRecruitContract {

    interface View extends BaseContract.BaseView {

        void setClinical(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialListBeanList,@LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadClinicals();

        void searchClinical(String s);

    }

}
