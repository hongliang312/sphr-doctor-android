package com.lightheart.sphr.doctor.module.contracts.ui;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.adapter.ContractsAdapter;
import com.lightheart.sphr.doctor.module.contracts.contract.SearchDoctorContract;
import com.lightheart.sphr.doctor.module.contracts.presenter.SearchDoctorPresenter;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by fucp on 2018-5-10.
 * Description : 添加好友页面
 */

public class SearchPhoneActivity extends BaseActivity<SearchDoctorPresenter> implements SearchDoctorContract.View, BaseQuickAdapter.RequestLoadMoreListener, ContractsAdapter.SlideItemListener, TextWatcher {

    @BindView(R.id.mainBar)
    AppBarLayout mainBar;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.etDisease)
    EditText etDisease;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvDoctors)
    RecyclerView mRvDoctors;
    private PublishSubject<String> mSubject = PublishSubject.create();
    @Inject
    ContractsAdapter mContractsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_phone;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.add_contract, false, 0);

        mContractsAdapter.initData(this, "SEARCH");
        //  设置RecyclerView
        mRvDoctors.setLayoutManager(new LinearLayoutManager(this));
        mRvDoctors.setAdapter(mContractsAdapter);

        mContractsAdapter.setOnSlideItemListener(this);
        mContractsAdapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setEnabled(false);

        etDisease.addTextChangedListener(this);

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

    @OnClick({R.id.ivDelete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDelete:
                etDisease.setText(getString(R.string.empty));
                break;
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String phone = charSequence.toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            ivDelete.setVisibility(View.VISIBLE);
        } else {
            ivDelete.setVisibility(View.INVISIBLE);
        }
        queryWithRxJava(phone);
    }

    private void queryDoctor(String phone) {
        assert mPresenter != null;
        mPresenter.loadDoctors(phone);
    }

    private void queryWithRxJava(String newText) {
        mSubject.onNext(newText);
    }

    @Override
    public void setSearchDoctors(List<DoctorBean> contractDocList, int loadType) {
        setLoadDataResult(mContractsAdapter, mSwipeRefreshLayout, contractDocList, loadType);
        if (contractDocList != null && contractDocList.size() == 0)
            initEmptyView(mContractsAdapter, mRvDoctors);
    }

    @Override
    public void onLoadMoreRequested() {
        assert mPresenter != null;
        mPresenter.loadMore();
    }

    @Override
    public void itemClick(View view, int position, DoctorBean item) {
        assert item != null;
        startActivity(new Intent(this, MyHomePageActivity.class).putExtra("duid", item.getId()));
    }

    @Override
    public void accept(View view, int position, DoctorBean item) {
    }

    @Override
    public void deleteClick(View view, int position, DoctorBean item) {
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
