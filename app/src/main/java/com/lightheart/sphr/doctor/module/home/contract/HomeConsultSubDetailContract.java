package com.lightheart.sphr.doctor.module.home.contract;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ConsultingReplyBean;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetailRequestParams;



public interface HomeConsultSubDetailContract {

    interface View extends BaseContract.BaseView{

        void setHomeConsultSubDetailData(HomeConsultSubDetail content);

        void setConsultingReply();
    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadHomeConsultSubDetailData(HomeConsultSubDetailRequestParams subDetailRequestParams);

        void loadConsultingReplyData(ConsultingReplyBean consultingReplyBean);
    }
}
