package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.PanelMessageModel;

import java.util.List;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public interface PanelMessageListContract {

    interface View extends BaseContract.BaseView {

        void setPanelMessage(List<PanelMessageModel> panelMessageModels, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<PanelMessageListContract.View> {

        void loadPanelMessage();

        void updateApplyDtm(PanelMessageModel param);

        void refresh();

    }


}
