package com.lightheart.sphr.doctor.module.home.contract;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.UntreatedBean;

import java.util.List;


/**
 * Created by 知足 on 2018/5/14.
 */

public interface UntreatedContract {

    interface View extends BaseContract.BaseView{

        void setUntreated(List<UntreatedBean> content);


    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadUntreatedData(String type);

    }
}
