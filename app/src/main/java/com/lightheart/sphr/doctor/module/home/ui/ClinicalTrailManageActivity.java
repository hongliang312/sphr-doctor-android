package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.ClinicalTrailModel;
import com.lightheart.sphr.doctor.module.home.adapter.ClinicalTrailManageAdapter;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalTrailManageContract;
import com.lightheart.sphr.doctor.module.home.presenter.ClinicalTrailManagePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ClinicalTrailManageActivity extends BaseActivity<ClinicalTrailManagePresenter> implements ClinicalTrailManageContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvClinical)
    RecyclerView mRvClinical;
    @Inject
    ClinicalTrailManageAdapter manageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clinical_trail_manage;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.testingmanagement, false, 0);

        mRvClinical.setLayoutManager(new LinearLayoutManager(this));

        View clinicalHeader = LayoutInflater.from(this).inflate(R.layout.header_clinical_trail, null);
        manageAdapter.addHeaderView(clinicalHeader);
        mRvClinical.setAdapter(manageAdapter);

        manageAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadClinicalData();

    }

    @Override
    public void setClinicalData(List<ClinicalTrailModel> content, int loadType) {
        setLoadDataResult(manageAdapter, mSwipeRefreshLayout, content, loadType);
    }


    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ClinicalTrailModel item = (ClinicalTrailModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(this, ClinicalTrailManageDetailActivity.class).putExtra("id", item.getId()));
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }
}
