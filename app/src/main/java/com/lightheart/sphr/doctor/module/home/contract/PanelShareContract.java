package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.Apply2PanelParam;
import com.lightheart.sphr.doctor.bean.PanelShareModel;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface PanelShareContract {

    interface View extends BaseContract.BaseView {

        void setPanelShare(List<PanelShareModel> panelsModels, @LoadType.checker int loadType);

        void success2ApplyPanel();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadPanelShare(int id);

        void apply2Panel(Apply2PanelParam param);

    }

}
