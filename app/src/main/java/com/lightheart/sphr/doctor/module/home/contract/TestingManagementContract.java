package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.TestingManagement;

import java.util.List;

/**
 * Created by 知足 on 2018/5/9.
 */

public interface TestingManagementContract {

    interface View extends BaseContract.BaseView{

      void setTesting(List<TestingManagement> content, int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadTestData();
        void refresh();

    }
}
