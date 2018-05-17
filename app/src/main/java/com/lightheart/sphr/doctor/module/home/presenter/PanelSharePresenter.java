package com.lightheart.sphr.doctor.module.home.presenter;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.Apply2PanelParam;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.PanelShareModel;
import com.lightheart.sphr.doctor.bean.PanelShareParam;
import com.lightheart.sphr.doctor.module.home.contract.PanelShareContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-15.
 * Description :
 */

public class PanelSharePresenter extends BasePresenter<PanelShareContract.View> implements PanelShareContract.Presenter {

    private boolean mIsRefresh;
    private PanelShareParam param = new PanelShareParam();

    @Inject
    public PanelSharePresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadPanelShare(int id) {
        param.id = id;
        RetrofitManager.create(ApiService.class)
                .shareListByDtmAroId(param)
                .compose(RxSchedulers.<DataResponse<List<PanelShareModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<PanelShareModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<PanelShareModel>>>() {
                    @Override
                    public void accept(DataResponse<List<PanelShareModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setPanelShare(response.getContent(), loadType);
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void apply2Panel(Apply2PanelParam param) {
        RetrofitManager.create(ApiService.class)
                .addApplyDtm(param)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.success2ApplyPanel();
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

}
