package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.FeedBackBean;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface FeedBackContract {

    interface View extends BaseContract.BaseView {

        void successSub();

    }

    interface Presenter extends BaseContract.BasePresenter<FeedBackContract.View> {

        void submitFeedBack(FeedBackBean feedBackBean);

    }

}
