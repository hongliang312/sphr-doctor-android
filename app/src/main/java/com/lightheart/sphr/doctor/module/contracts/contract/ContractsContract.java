package com.lightheart.sphr.doctor.module.contracts.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;

import java.util.List;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface ContractsContract {

    interface View extends BaseContract.BaseView {

        void setContracts(List<DoctorBean> ContractDocList, @LoadType.checker int loadType);

        void successInvite();

    }

    interface Presenter extends BaseContract.BasePresenter<ContractsContract.View> {

        void loadContractData();

        void deleteDoc(DocContractRequestParams params);

        void refresh();

        void loadMore();

        void invite2Panel();

    }

}
