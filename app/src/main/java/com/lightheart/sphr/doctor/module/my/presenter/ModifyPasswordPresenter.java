package com.lightheart.sphr.doctor.module.my.presenter;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.ModifyPsdParam;
import com.lightheart.sphr.doctor.module.my.contract.ModifyPasswordContract;
import com.lightheart.sphr.doctor.module.my.contract.ModifyPasswordContract.Presenter;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public class ModifyPasswordPresenter extends BasePresenter<ModifyPasswordContract.View> implements Presenter {

    @Inject
    public ModifyPasswordPresenter() {
    }

    @Override
    public void getToken() {
        RetrofitManager.create(ApiService.class)
                .getToken(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<String>>applySchedulers())
                .compose(mView.<DataResponse<String>>bindToLife())
                .subscribe(new Consumer<DataResponse<String>>() {
                    @Override
                    public void accept(DataResponse<String> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.getToken(response.getContent());
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
    public void modifyPsd(ModifyPsdParam param) {
        RetrofitManager.create(ApiService.class)
                .modifyPsd(param)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.successModify();
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
