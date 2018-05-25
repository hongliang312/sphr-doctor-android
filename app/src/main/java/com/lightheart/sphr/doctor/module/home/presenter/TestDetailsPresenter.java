package com.lightheart.sphr.doctor.module.home.presenter;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.module.home.contract.TestDetailsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 知足 on 2018/5/11.
 */

public class TestDetailsPresenter extends BasePresenter<TestDetailsContract.View> implements TestDetailsContract.Presenter {

    @Inject
    public TestDetailsPresenter() {

    }
    @Override
    public void loadDetailsData(int id) {
        DetailsBean entity = new DetailsBean();
        entity.duid= SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        entity.id=id;

        RetrofitManager.create(ApiService.class)
                .detailslist(entity)
                .compose(RxSchedulers.<DataResponse<TestDetails>>applySchedulers())
                .compose(mView.<DataResponse<TestDetails>>bindToLife())
                .subscribe(new Consumer<DataResponse<TestDetails>>() {
                   @Override
                   public void accept(DataResponse<TestDetails> response) throws Exception {

                       if(response.getResultcode()==200) {

                           mView.setDetals(response.getContent());

                           Log.i("yyy",""+response.getContent());

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
