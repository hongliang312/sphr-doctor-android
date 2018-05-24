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

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PatientsModel;
import com.lightheart.sphr.doctor.module.home.activity.PatientRecordsActivity;
import com.lightheart.sphr.doctor.module.home.adapter.PatientsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientManageContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientsPresenter;
import com.lightheart.sphr.doctor.view.CommonTabLayout;
import com.lightheart.sphr.doctor.view.CustomTabEntity;
import com.lightheart.sphr.doctor.view.OnTabSelectListener;
import com.lightheart.sphr.doctor.view.TabEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-14.
 * Description : 患者管理
 */

public class HomePatientManageActivity extends BaseActivity<PatientsPresenter> implements PatientManageContract.View, OnTabSelectListener, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvPatients)
    RecyclerView mRvPatients;
    @Inject
    PatientsAdapter mPatientsAdapter;
    private final String[] mTitles = {"3个月", "3个月-1年", "1年以上"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconSelectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int mPage = 1;
    private int timeCategory = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_patient_manage;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.patient_manage, false, 0);

        //  设置RecyclerView
        mRvPatients.setLayoutManager(new LinearLayoutManager(this));
        mRvPatients.setAdapter(mPatientsAdapter);

        // 设置HeadView
        View mPatientHeadView = LayoutInflater.from(this).inflate(R.layout.layout_patient_manage_head, null, false);
        TextView tvSearch = mPatientHeadView.findViewById(R.id.tvSearch);
        CommonTabLayout tabPatient = mPatientHeadView.findViewById(R.id.tabPatient);
        mPatientsAdapter.addHeaderView(mPatientHeadView);
        tvSearch.setOnClickListener(this);

        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabPatient.setTabData(mTabEntities);
        tabPatient.setCurrentTab(0);
        tabPatient.setOnTabSelectListener(this);

        mPatientsAdapter.setOnItemClickListener(this);
        mPatientsAdapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadPatientData(mPage, timeCategory);

    }

    @Override
    public void onTabSelect(int position) {
        mPage = 1;
        timeCategory = position + 1;
        if (position == 0) {
            assert mPresenter != null;
            mPresenter.loadPatientData(mPage, timeCategory);
        } else if (position == 1) {
            assert mPresenter != null;
            mPresenter.loadPatientData(mPage, timeCategory);
        } else if (position == 2) {
            assert mPresenter != null;
            mPresenter.loadPatientData(mPage, timeCategory);
        }
    }

    @Override
    public void setPatients(List<PatientsModel.PatientModel> patientModels, int loadType) {
        setLoadDataResult(mPatientsAdapter, mSwipeRefreshLayout, patientModels, loadType);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                ToastUtils.showShort(getString(R.string.patient_search));
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        assert mPresenter != null;
        mPresenter.loadMore(mPage, timeCategory);
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh(mPage, timeCategory);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PatientsModel.PatientModel item = (PatientsModel.PatientModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(HomePatientManageActivity.this, PatientRecordsActivity.class).putExtra("id", item.duid));
    }
}
