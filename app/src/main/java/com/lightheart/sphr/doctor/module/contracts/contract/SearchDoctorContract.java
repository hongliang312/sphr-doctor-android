package com.lightheart.sphr.doctor.module.contracts.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;

import java.util.List;

/**
 * Created by fucp on 2018-5-10.
 * Description :
 */

public interface SearchDoctorContract {

    interface View extends BaseContract.BaseView {

        void setSearchDoctors(List<ContractDocItem> ContractDocList, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<SearchDoctorContract.View> {

        void loadDoctors(String mobile);

        void refresh();

        void loadMore();

    }

}
