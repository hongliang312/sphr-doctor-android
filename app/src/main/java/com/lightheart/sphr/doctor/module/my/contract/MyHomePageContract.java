package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.RequestParams;

/**
 * Created by fucp on 2018-5-12.
 * Description :
 */

public interface MyHomePageContract {

    interface View extends BaseContract.BaseView {

        void setAddFriendView(boolean isFriend);

        void setData(DoctorBean docInfo);

        void successAdd();

    }

    interface Presenter extends BaseContract.BasePresenter<MyHomePageContract.View> {

        void isAddFriend(RequestParams params);

        void loadDoc(int id);

        void toAddFriend(RequestParams params);

    }

}
