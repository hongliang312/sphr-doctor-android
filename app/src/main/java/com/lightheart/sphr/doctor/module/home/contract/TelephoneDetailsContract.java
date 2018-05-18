package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsBean;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsRequestParams;

/**
 * Created by 知足 on 2018/5/16.
 */

public interface TelephoneDetailsContract {

    interface View extends BaseContract.BaseView{

        void setTelephoneDetails(TelephoneDetailsBean content);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadTelephoneDetailsData(TelephoneDetailsRequestParams telephondetails);


    }
}
