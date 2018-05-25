package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.CreatePanelDoctorParam;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.PanelMessageModel;
import com.lightheart.sphr.doctor.module.home.contract.PanelMessageListContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public class PanelMessageListPresenter extends BasePresenter<PanelMessageListContract.View> implements PanelMessageListContract.Presenter {

    private CreatePanelDoctorParam param = new CreatePanelDoctorParam();
    private boolean mIsRefresh;
    private int mPage = 1;

    @Inject
    public PanelMessageListPresenter() {
        this.mIsRefresh = true;
        param.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    }

    @Override
    public void loadPanelMessage() {
        RetrofitManager.create(ApiService.class)
                .getDtmApplyList(param)
                .compose(RxSchedulers.<DataResponse<List<PanelMessageModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<PanelMessageModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<PanelMessageModel>>>() {
                    @Override
                    public void accept(DataResponse<List<PanelMessageModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setPanelMessage(response.getContent(), loadType);
                        } else {
                            mView.showFaild(response.getResultmsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void updateApplyDtm(PanelMessageModel param) {
        RetrofitManager.create(ApiService.class)
                .updateApplyDtm(param)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.showSuccess(String.valueOf(response.getContent()));
                            loadPanelMessage();
                        } else {
                            mView.showFaild(response.getResultmsg());
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
    public void refresh() {
        mIsRefresh = true;
        loadPanelMessage();
    }
}
