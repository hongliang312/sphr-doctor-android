package com.lightheart.sphr.doctor.module.home.contract;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ConsultingReplyRequestParams;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetailRequestParams;


public interface HomeConsultSubDetailContract {

    interface View extends BaseContract.BaseView{

        void setHomeConsultSubDetailData(HomeConsultSubDetail content);

        void successReply();

    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadHomeConsultSubDetailData(HomeConsultSubDetailRequestParams subDetailRequestParams);

        void replyConsult(ConsultingReplyRequestParams replyConsultingbean);

       void loadTelDetailsData(HomeConsultSubDetailRequestParams subDetailRequestParams);
    }
}
