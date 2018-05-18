package com.lightheart.sphr.doctor.module.home.presenter;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.TestingManagement;
import com.lightheart.sphr.doctor.bean.UntreatedBean;
import com.lightheart.sphr.doctor.bean.UntreatedRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.UntreatedContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


/**
 * Created by 知足 on 2018/5/14.
 */

public class UntreatedPresenter extends BasePresenter<UntreatedContract.View> implements UntreatedContract.Presenter{

     private  UntreatedRequestParams untreated = new  UntreatedRequestParams();
    @Inject
    public UntreatedPresenter() {
        this.untreated.duid= SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    }

    @Override
    public void loadUntreatedData(String type) {
        this.untreated.type=type;
        RetrofitManager.create(ApiService.class)
                .pendinglist(untreated)
                .compose(RxSchedulers.<DataResponse<List<UntreatedBean>>>applySchedulers())
                .compose(mView.<DataResponse<List<UntreatedBean>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<UntreatedBean>>>() {
                    @Override
                    public void accept(DataResponse<List<UntreatedBean>>response) throws Exception {

                        if(response.getResultcode() == 200){

                            mView.setUntreated(response.getContent());
                            Log.i("oooo",""+response.getContent());
                        }else {

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
