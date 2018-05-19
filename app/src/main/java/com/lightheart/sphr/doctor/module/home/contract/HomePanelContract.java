package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.bean.ShareClinical2PanelParam;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface HomePanelContract {

    interface View extends BaseContract.BaseView {

        void setPanelData(List<PanelsModel> panelsModels, @LoadType.checker int loadType, String isMember);

        void successShare();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadPanelList(String isMember);

        void refresh();

        void share2Panel(ShareClinical2PanelParam param);

    }

}
