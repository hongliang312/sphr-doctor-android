package com.lightheart.sphr.doctor.module.my.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.lightheart.sphr.doctor.base.AuthParam;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.module.my.contract.AuthenticationContract;
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
 * Created by fucp on 2018-5-23.
 * Description :
 */

public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    @Inject
    public AuthenticationPresenter() {
    }

    @Override
    public void uploadImage(File file) {
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
    public void updatePersonalInfo(AuthParam param) {
        RetrofitManager.create(ApiService.class)
                .auth(param)
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
