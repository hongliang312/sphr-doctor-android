package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.module.home.adapter.HomeClinicalRecruitAdapter;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalRecruitContract;
import com.lightheart.sphr.doctor.module.home.presenter.ClinicalRecruitPresenter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by fucp on 2018-5-17.
 * Description : 临床招募
 */

public class HomeClinicalRecruitActivity extends BaseActivity<ClinicalRecruitPresenter> implements ClinicalRecruitContract.View, BaseQuickAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.shvDisease)
    SearchView shvDisease;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvClinicals)
    RecyclerView mRvClinicals;
    @Inject
    HomeClinicalRecruitAdapter mAdapter;
    private PublishSubject<String> mSubject = PublishSubject.create();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clinical_recruit;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.clinical_trial, false, 0);

        // 设置RecyclerView
        mRvClinicals.setLayoutManager(new LinearLayoutManager(this));
        mRvClinicals.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setEnabled(false);

        assert mPresenter != null;
        mPresenter.loadClinicals();

        // 通过适应症搜索
        shvDisease.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ToastUtils.showShort("Close");
                assert mPresenter != null;
                mPresenter.loadClinicals();
                return true;
            }
        });
//        shvDisease.onActionViewExpanded();
        shvDisease.setOnQueryTextListener(this);
        mSubject.debounce(600, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        queryDoctor(s);
                    }
                }).subscribe();
    }

    private void queryDoctor(String s) {
        assert mPresenter != null;
        mPresenter.searchClinical(s);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!TextUtils.isEmpty(newText.trim())) {
            queryWithRxJava(newText);
        }
        return true;
    }

    private void queryWithRxJava(String newText) {
        mSubject.onNext(newText);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomePageInfo.ClinicalTrialListBean item = (HomePageInfo.ClinicalTrialListBean) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(HomeClinicalRecruitActivity.this, ClinicalRecruitDetailActivity.class).putExtra("id", item.getId()));
    }

    @Override
    public void setClinical(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialListBeanList, int loadType) {
        setLoadDataResult(mAdapter, mSwipeRefreshLayout, clinicalTrialListBeanList, loadType);
    }

    // 暂时不用
    @Override
    public void setDoctorInfo(DoctorBean docInfo) {

    }

    // 暂时不用
    @Override
    public void setClinicalRecruitDetail(HomePageInfo.ClinicalTrialListBean clinicalTrialListBeanList, int loadType) {
    }

    // 暂时不用
    @Override
    public void successApply() {
    }

}
