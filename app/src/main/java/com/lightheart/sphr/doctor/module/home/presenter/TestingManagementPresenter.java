package com.lightheart.sphr.doctor.module.home.presenter;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.TestingManagement;
import com.lightheart.sphr.doctor.bean.TextsingRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.TestingManagementContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 知足 on 2018/5/9.
 */

public class TestingManagementPresenter extends BasePresenter<TestingManagementContract.View> implements TestingManagementContract.Presenter{


    private TextsingRequestParams requestParams = new TextsingRequestParams();

    @Inject
    public TestingManagementPresenter() {
        this.requestParams.duid = 8520;
    }

    @Override
    public void loadTestData() {
        RetrofitManager.create(ApiService.class)
                .Testinglist(requestParams)
                .compose(RxSchedulers.<DataResponse<List<TestingManagement>>>applySchedulers())
                .compose(mView.<DataResponse<List<TestingManagement>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<TestingManagement>>>() {
                    @Override
                    public void accept(DataResponse<List<TestingManagement>> response) throws Exception {

                        if (response.getResultcode()==200){

                            mView.setTesting(response.getContent());

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
