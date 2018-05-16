package com.lightheart.sphr.doctor.module.my.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.IsFriendModel;
import com.lightheart.sphr.doctor.bean.RequestParams;
import com.lightheart.sphr.doctor.module.my.contract.MyHomePageContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.io.ObjectInput;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-12.
 * Description :
 */

public class MyHomePagePresenter extends BasePresenter<MyHomePageContract.View> implements MyHomePageContract.Presenter {

    private DocContractRequestParams params = new DocContractRequestParams();

    @Inject
    public MyHomePagePresenter() {
    }

    @Override
    public void isAddFriend(RequestParams params) {
        RetrofitManager.create(ApiService.class)
                .checkFriend(params)
                .compose(RxSchedulers.<DataResponse<IsFriendModel>>applySchedulers())
                .compose(mView.<DataResponse<IsFriendModel>>bindToLife())
                .subscribe(new Consumer<DataResponse<IsFriendModel>>() {
                    @Override
                    public void accept(DataResponse<IsFriendModel> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setAddFriendView(response.getContent().result == 0);
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
    public void loadDoc(int id) {
        this.params.duid = id;
        RetrofitManager.create(ApiService.class)
                .getDocInfo(params)
                .compose(RxSchedulers.<DataResponse<DoctorBean>>applySchedulers())
                .compose(mView.<DataResponse<DoctorBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DoctorBean>>() {
                    @Override
                    public void accept(DataResponse<DoctorBean> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setData(response.getContent());
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
    public void toAddFriend(RequestParams params) {
        RetrofitManager.create(ApiService.class)
                .applyAddDoc(params)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.successAdd();
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
