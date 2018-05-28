package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.ConsultModel;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.ConsultingListRequestParams;
import com.lightheart.sphr.doctor.bean.TelephoneConsultBean;
import com.lightheart.sphr.doctor.module.home.contract.ConsultingListContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


public class ConsultListPresenter extends BasePresenter<ConsultingListContract.View> implements ConsultingListContract.Presenter {

    private ConsultingListRequestParams params = new ConsultingListRequestParams();

    @Inject
    ConsultListPresenter() {
//        params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        params.duid = 1010;
    }
    @Override
    public void loadOnlineData(String type) {
        params.type = type;
        RetrofitManager.create(ApiService.class)
                .getConsultList(params)
                .compose(RxSchedulers.<DataResponse<List<ConsultModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<ConsultModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<ConsultModel>>>() {
                    @Override
                    public void accept(DataResponse<List<ConsultModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setOnlineData(response.getContent());
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
    public void loadTelConsultData(String type) {
        params.type = type;
        RetrofitManager.create(ApiService.class)
                .getTelConsultList(params)
                .compose(RxSchedulers.<DataResponse<List<ConsultModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<ConsultModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<ConsultModel>>>() {
                    @Override
                    public void accept(DataResponse<List<ConsultModel>> responsee) throws Exception {
                        if (responsee.getResultcode() == 200) {
                            mView.setTelConsultData(responsee.getContent());
                        } else {
                            mView.showFaild(String.valueOf(responsee.getResultmsg()));
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
