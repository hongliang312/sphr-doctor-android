package com.lightheart.sphr.doctor.module.home.presenter;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
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
    private boolean mIsRefresh;
    @Inject
    public TestingManagementPresenter() {
        this.requestParams.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        this.mIsRefresh = true;
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
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;

                            mView.setTesting(response.getContent(),loadType);

                            Log.i("ddd",""+response.getContent());

                        }else {

                            mView.showFaild(String.valueOf(response.getResultmsg()));

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.showFaild(throwable.getMessage());

                    }
                });

      }
    @Override
    public void refresh() {
        mIsRefresh = true;
        loadTestData();
    }
}
