package com.lightheart.sphr.doctor.module.home.presenter;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.ConsultingListBean;
import com.lightheart.sphr.doctor.bean.ConsultingListRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.ConsultingListContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;



public class ConsultingListPresenter extends BasePresenter<ConsultingListContract.View> implements ConsultingListContract.Presenter{

     private ConsultingListRequestParams untreated = new ConsultingListRequestParams();
    @Inject
    ConsultingListPresenter() {
        this.untreated.duid= 1010;//1010
    }

    @Override
    public void loadConsultingListData(String type) {
        this.untreated.type=type;
        RetrofitManager.create(ApiService.class)
                .pendinglist(untreated)
                .compose(RxSchedulers.<DataResponse<List<ConsultingListBean>>>applySchedulers())
                .compose(mView.<DataResponse<List<ConsultingListBean>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<ConsultingListBean>>>() {
                    @Override
                    public void accept(DataResponse<List<ConsultingListBean>>response) throws Exception {
                        if(response.getResultcode() == 200){
                            mView.setConsultingListData(response.getContent());
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
