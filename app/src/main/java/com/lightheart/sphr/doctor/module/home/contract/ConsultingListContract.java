package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.ConsultModel;

import java.util.List;

public interface ConsultingListContract {

    interface View extends BaseContract.BaseView {

        void setOnlineData(List<ConsultModel> content);

        void setTelConsultData(List<ConsultModel> content);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadOnlineData(String type);// 请求在线咨询列表数据

        void loadTelConsultData(String type);// 请求电话咨询列表数据

    }
}
