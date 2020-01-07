package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;

/**
 * Created by fucp on 2018-5-28.
 * Description :
 */

public interface MyMessageSetContract {

    interface View extends BaseContract.BaseView {

        void setDefault(String isArticlePush, String isPersonalPush);

    }

    interface Presenter extends BaseContract.BasePresenter<MyMessageSetContract.View> {

        void loadMessageSetData();

        void setNewsPush(boolean push);

        void setPersonPush(boolean push);

    }

}
