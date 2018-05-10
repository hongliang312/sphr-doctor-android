package com.lightheart.sphr.doctor.module.contracts.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;

import java.util.List;

/**
 * Created by fucp on 2018-5-9.
 * Description :
 */

public interface NewContractsContract {

    interface View extends BaseContract.BaseView {

        void setNewContracts(List<ContractDocItem> ContractDocList, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<NewContractsContract.View> {

        void loadNewContractData();

        void operateDoc(DocContractRequestParams params);

        void refresh();

        void loadMore();

    }

}
