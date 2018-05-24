package com.lightheart.sphr.doctor.module.my.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.DoctorModel;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.PanelShareParam;
import com.lightheart.sphr.doctor.bean.TitlesModel;
import com.lightheart.sphr.doctor.module.my.contract.MyPersonalInfoContract;
import com.lightheart.sphr.doctor.net.ApiFile;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.FileUploadObserver;
import com.lightheart.sphr.doctor.net.RetrofitClient;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by fucp on 2018-5-22.
 * Description :
 */

public class MyPersonalInfoPresenter extends BasePresenter<MyPersonalInfoContract.View> implements MyPersonalInfoContract.Presenter {

    @Inject
    public MyPersonalInfoPresenter() {
    }

    @Override
    public void loadTitleData() {
        RetrofitManager.create(ApiService.class)
                .getTitles(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<List<TitlesModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<TitlesModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<TitlesModel>>>() {
                    @Override
                    public void accept(DataResponse<List<TitlesModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setTitles(response.getContent());
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
    public void loadHospitalData(int distractId) {
        PanelShareParam panelShareParam = new PanelShareParam();
        panelShareParam.id = distractId;
        RetrofitManager.create(ApiService.class)
                .getHosptialListById(panelShareParam)
                .compose(RxSchedulers.<DataResponse<List<HospitalsModel>>>applySchedulers())
                .compose(mView.<DataResponse<List<HospitalsModel>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<HospitalsModel>>>() {
                    @Override
                    public void accept(DataResponse<List<HospitalsModel>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.setHospitals(response.getContent());
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
    public void uploadHeadImage(File file) {
        RetrofitClient.getInstance().upLoadFile(ApiFile.UPLOAD_FILE_URL, file,
                new FileUploadObserver<ResponseBody>() {
                    @Override
                    public void onUploadSuccess(ResponseBody responseBody) {
                        try {
                            if (responseBody != null) {
                                String s = new JSONObject(responseBody.string()).toString();
                                DataResponse dataResponse = new Gson().fromJson(s, DataResponse.class);
                                if (dataResponse.getResultcode() == 200) {
                                    List<String> content = (List<String>) dataResponse.getContent();
                                    if (content.size() > 0)
                                        mView.successUploadImage(content.get(0));
                                }
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onUploadFail(Throwable e) {
                    }

                    @Override
                    public void onProgress(int progress) {
                        LogUtils.d(String.valueOf(progress));
                    }
                });
    }

    @Override
    public void updatePersonalInfo(DoctorModel info) {
        RetrofitManager.create(ApiService.class)
                .updateInfo(info)
                .compose(RxSchedulers.<DataResponse<Object>>applySchedulers())
                .compose(mView.<DataResponse<Object>>bindToLife())
                .subscribe(new Consumer<DataResponse<Object>>() {
                    @Override
                    public void accept(DataResponse<Object> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            mView.updateSuccess();
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
