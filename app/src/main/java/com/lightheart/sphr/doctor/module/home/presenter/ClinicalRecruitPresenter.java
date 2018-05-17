package com.lightheart.sphr.doctor.module.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BasePresenter;
import com.lightheart.sphr.doctor.bean.ClinicalRecruitModel;
import com.lightheart.sphr.doctor.bean.ClinicalSearchParam;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.PanelShareModel;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalRecruitContract;
import com.lightheart.sphr.doctor.net.ApiService;
import com.lightheart.sphr.doctor.net.RetrofitManager;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-5-17.
 * Description :
 */

public class ClinicalRecruitPresenter extends BasePresenter<ClinicalRecruitContract.View> implements ClinicalRecruitContract.Presenter {

    private boolean mIsRefresh;
    private ClinicalSearchParam param = new ClinicalSearchParam();

    @Inject
    public ClinicalRecruitPresenter() {
        this.mIsRefresh = true;
        param.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    }

    @Override
    public void loadClinicals() {
        RetrofitManager.create(ApiService.class)
                .getAllClinicalTrial(new LoginSuccess())
                .compose(RxSchedulers.<DataResponse<ClinicalRecruitModel>>applySchedulers())
                .compose(mView.<DataResponse<ClinicalRecruitModel>>bindToLife())
                .subscribe(new Consumer<DataResponse<ClinicalRecruitModel>>() {
                    @Override
                    public void accept(DataResponse<ClinicalRecruitModel> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setClinical(response.getContent().clinicalTrials, loadType);
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
    public void searchClinical(String s) {
        param.keyWord = s;
        RetrofitManager.create(ApiService.class)
                .searchClinicalTrial(param)
                .compose(RxSchedulers.<DataResponse<List<HomePageInfo.ClinicalTrialListBean>>>applySchedulers())
                .compose(mView.<DataResponse<List<HomePageInfo.ClinicalTrialListBean>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<HomePageInfo.ClinicalTrialListBean>>>() {
                    @Override
                    public void accept(DataResponse<List<HomePageInfo.ClinicalTrialListBean>> response) throws Exception {
                        if (response.getResultcode() == 200) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setClinical(response.getContent(), loadType);
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
