package com.lightheart.sphr.doctor.module.home.presenter;

import android.util.Log;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DetailsEntity;
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

    private DetailsEntity entity = new DetailsEntity();

    @Inject
    public TestDetailsPresenter() {
       this.entity.getDuid();
       this.entity.getId();
    }

    @Override
    public void loadDetailsData(DetailsEntity entity) {

        Log.i("id",""+entity);

        RetrofitManager.create(ApiService.class)
                .detailslist(entity)
                .compose(RxSchedulers.<DataResponse<TestDetails>>applySchedulers())
                .compose(mView.<DataResponse<TestDetails>>bindToLife())

                .subscribe(new Consumer<DataResponse<TestDetails>>() {
                   @Override
                   public void accept(DataResponse<TestDetails> response) throws Exception {

                       if(response.getResultcode()==200)
                           mView.setDetals(response.getContent());

                       else {
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
