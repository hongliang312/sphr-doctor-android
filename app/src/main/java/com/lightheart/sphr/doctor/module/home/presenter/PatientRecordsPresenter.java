package com.lightheart.sphr.doctor.module.home.presenter;
import android.util.Log;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;
import com.lightheart.sphr.doctor.module.home.contract.PatientRecordsContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;

/**
 * Created by 知足 on 2018/5/16.
 */

public class PatientRecordsPresenter extends BasePresenter<PatientRecordsContract.View> implements PatientRecordsContract.Presenter{

     @Inject
    public PatientRecordsPresenter() {

    }

    @Override
    public void loadPatientRecordsData(PatientRecordsRequestParams Params) {
        RetrofitManager.create(ApiService.class)
                .clientcentlist(Params)
                .compose(RxSchedulers.<DataResponse<PatientRecordsBean>>applySchedulers())
                .compose(mView.<DataResponse<PatientRecordsBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<PatientRecordsBean>>() {
                    @Override
                    public void accept(DataResponse<PatientRecordsBean>response) throws Exception {

                        if(response.getResultcode() == 200){

                            mView.setPatientRecords(response.getContent());
                            Log.i("dddd",""+response.getContent());

                        }else{

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
