package com.lightheart.sphr.doctor.module.my.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.contract.ContractsContract;

import java.util.List;

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
