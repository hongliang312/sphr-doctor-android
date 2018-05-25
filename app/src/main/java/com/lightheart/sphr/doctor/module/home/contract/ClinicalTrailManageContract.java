package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ClinicalTrailModel;

import java.util.List;

public interface ClinicalTrailManageContract {

    interface View extends BaseContract.BaseView {

        void setClinicalData(List<ClinicalTrailModel> content, int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadClinicalData();

        void refresh();

    }
}
