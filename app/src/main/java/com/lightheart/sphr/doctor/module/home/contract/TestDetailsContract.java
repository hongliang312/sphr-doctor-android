package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.TestDetails;

/**
 * Created by 知足 on 2018/5/11.
 */

public interface TestDetailsContract {
    interface View extends BaseContract.BaseView{

        void setDetals(TestDetails content);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadDetailsData(DetailsBean entity);


    }
}
