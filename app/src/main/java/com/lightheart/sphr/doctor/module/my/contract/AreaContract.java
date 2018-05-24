package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.AreaModel;

import java.util.List;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface AreaContract {

    interface View extends BaseContract.BaseView {

        void setAreas(List<AreaModel> titles);

        void setChildAreas(List<AreaModel> childAreas);

        void setDepartment(List<AreaModel> departments);

    }

    interface Presenter extends BaseContract.BasePresenter<AreaContract.View> {

        void loadAreaData();

        void loadChildAreaData(int id);

        void loadDepartmentData();

    }

}
