package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.CreatePanelParam;
import com.lightheart.sphr.doctor.bean.DiseaseModel;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface CreatePanelContract {

    interface View extends BaseContract.BaseView {

        void createPanelSuccess();

        void setDiseases(List<DiseaseModel> diseaseModelList);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadDiseaseData();

        void createPanel(CreatePanelParam param);

    }

}
