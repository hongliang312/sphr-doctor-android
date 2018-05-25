package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ModifyPsdParam;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public interface ModifyPasswordContract {

    interface View extends BaseContract.BaseView {

        void getToken(String token);

        void successModify();

    }

    interface Presenter extends BaseContract.BasePresenter<ModifyPasswordContract.View> {

        void getToken();

        void modifyPsd(ModifyPsdParam param);

    }

}
