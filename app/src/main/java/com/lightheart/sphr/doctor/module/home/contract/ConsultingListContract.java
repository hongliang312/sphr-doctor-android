package com.lightheart.sphr.doctor.module.home.contract;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ConsultingListBean;

import java.util.List;

public interface ConsultingListContract {

    interface View extends BaseContract.BaseView{

        void setConsultingListData(List<ConsultingListBean> content);

    }
    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadConsultingListData(String type);

    }
}
