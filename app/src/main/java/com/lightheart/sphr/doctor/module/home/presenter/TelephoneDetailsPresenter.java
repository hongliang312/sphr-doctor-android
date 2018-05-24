package com.lightheart.sphr.doctor.module.home.presenter;

import android.util.Log;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.ReplyConsultingBean;
import com.lightheart.sphr.doctor.bean.ReplyConsultingRequestParams;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsBean;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.TelephoneDetailsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


/**
 * Created by 知足 on 2018/5/16.
 */

public class TelephoneDetailsPresenter extends BasePresenter<TelephoneDetailsContract.View> implements TelephoneDetailsContract.Presenter {


    @Inject
    public TelephoneDetailsPresenter() {

    }

    @Override
    public void loadTelephoneDetailsData(TelephoneDetailsRequestParams telephondetails) {
        RetrofitManager.create(ApiService.class)
                .telephonedetails(telephondetails)
                .compose(RxSchedulers.<DataResponse<TelephoneDetailsBean>>applySchedulers())
                .compose(mView.<DataResponse<TelephoneDetailsBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<TelephoneDetailsBean>>() {
                    @Override
                    public void accept(DataResponse<TelephoneDetailsBean> response) throws Exception {

                        if(response.getResultcode() == 200){

                            mView.setTelephoneDetails(response.getContent());

                            Log.i("pppp",""+response.getContent());

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
    public void loadReplyConsultingData(ReplyConsultingBean replyConsultingbean) {

        RetrofitManager.create(ApiService.class)
                .backlist(replyConsultingbean)
                .compose(RxSchedulers.<ReplyConsultingBean> applySchedulers())
                .compose(mView.<ReplyConsultingBean>bindToLife())
                .subscribe(new Consumer<ReplyConsultingBean>() {
                    @Override
                    public void accept(ReplyConsultingBean replyConsultingBean) throws Exception {

                        if(replyConsultingBean.getResultcode()==200){

                            mView.setReplyConsulting();

                        }else {
                            mView.showFaild(String.valueOf(replyConsultingBean.getResultmsg()));
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
