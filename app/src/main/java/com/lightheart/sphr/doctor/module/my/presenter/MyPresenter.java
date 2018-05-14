package com.lightheart.sphr.doctor.module.my.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.module.my.contract.MyContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class MyPresenter extends BasePresenter<MyContract.View> implements MyContract.Presenter {

    private boolean mIsRefresh;
    private DocContractRequestParams params = new DocContractRequestParams();

    @Inject
    public MyPresenter() {
        this.mIsRefresh = true;
        this.params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    }

    @Override
    public void loadDocData() {
        RetrofitManager.create(ApiService.class)
                .getDocInfo(params)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setDocIndo(response.getContent());
                        } else {
                            mView.showFaild(String.valueOf(response.getResultmsg()));
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
    public void refresh() {
        mIsRefresh = true;
        loadDocData();
    }
}
