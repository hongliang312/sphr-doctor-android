package com.lightheart.sphr.doctor.module.contracts.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.module.home.contract.HomeContract;

import java.util.List;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public interface ContractsContract {

    interface View extends BaseContract.BaseView {

        void setClinicals(List<ContractDocItem> ContractDocList, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<ContractsContract.View> {

        void loadContractData();

        void refresh();

        void loadMore();

    }

}
