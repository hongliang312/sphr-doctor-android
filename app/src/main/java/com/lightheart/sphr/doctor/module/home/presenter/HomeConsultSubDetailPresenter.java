package com.lightheart.sphr.doctor.module.home.presenter;

import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.ConsultingReplyRequestParams;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.ConsultingReplyBean;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetailRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.HomeConsultSubDetailContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;

public class HomeConsultSubDetailPresenter extends BasePresenter<HomeConsultSubDetailContract.View> implements HomeConsultSubDetailContract.Presenter {


    @Inject
    HomeConsultSubDetailPresenter() {
    }
    @Override
    public void loadHomeConsultSubDetailData(HomeConsultSubDetailRequestParams subDetailRequestParams) {
        RetrofitManager.create(ApiService.class)
                .subDetail(subDetailRequestParams)
                .compose(RxSchedulers.<DataResponse<HomeConsultSubDetail>>applySchedulers())
                .compose(mView.<DataResponse<HomeConsultSubDetail>>bindToLife())
                .subscribe(new Consumer<DataResponse<HomeConsultSubDetail>>() {
                    @Override
                    public void accept(DataResponse<HomeConsultSubDetail> response) throws Exception {
                        if(response.getResultcode() == 200){
                            mView.setHomeConsultSubDetailData(response.getContent());
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
    public void loadConsultingReplyData(ConsultingReplyRequestParams replyConsultingbean) {
        RetrofitManager.create(ApiService.class)
                .consultingreply(replyConsultingbean)
                .compose(RxSchedulers.<ConsultingReplyBean> applySchedulers())
                .compose(mView.<ConsultingReplyBean>bindToLife())
                .subscribe(new Consumer<ConsultingReplyBean>() {
                    @Override
                    public void accept(ConsultingReplyBean replyConsultingBean) throws Exception {
                        if(replyConsultingBean.getResultcode()==200){
                            mView.setConsultingReply();
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



    @Override
    public void loadTelDetailsData(HomeConsultSubDetailRequestParams subDetailRequestParams) {
        RetrofitManager.create(ApiService.class)
                .getTelDetail(subDetailRequestParams)
                .compose(RxSchedulers.<DataResponse<HomeConsultSubDetail>>applySchedulers())
                .compose(mView.<DataResponse<HomeConsultSubDetail>>bindToLife())
                .subscribe(new Consumer<DataResponse<HomeConsultSubDetail>>() {
                    @Override
                    public void accept(DataResponse<HomeConsultSubDetail> responsee) throws Exception {
                        if(responsee.getResultcode() == 200){
                            mView.setTelDetailsData(responsee.getContent());
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
