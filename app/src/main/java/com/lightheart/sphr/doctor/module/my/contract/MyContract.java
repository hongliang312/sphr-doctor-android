package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorBean;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface MyContract {

    interface View extends BaseContract.BaseView {

        void setDocIndo(DoctorBean docIndo);

    }

    interface Presenter extends BaseContract.BasePresenter<MyContract.View> {

        void loadDocData();

        void refresh();

    }

}
