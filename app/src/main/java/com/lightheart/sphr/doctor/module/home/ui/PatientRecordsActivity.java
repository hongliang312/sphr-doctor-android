package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.module.home.adapter.PatientRecordsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientRecordsContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientRecordsPresenter;

import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;

public class PatientRecordsActivity extends BaseActivity<PatientRecordsPresenter> implements PatientRecordsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvRecord)
    RecyclerView rvRecord;
    @Inject
    PatientRecordsAdapter patientRecordsAdapter;

    private TextView tvName;
    private TextView tvAge;
    private TextView tvPlace;
    private TextView tvBirthPlace;
    private TextView tvNation;
    private TextView tvHistory1;
    private TextView tvHistory2;
    private TextView tvHistory3;
    private TextView tvSmokingHistory;
    private TextView tvDrink;
    private TextView tvObstericalHistory;
    private TextView tvFamilyHistory;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_patient_records;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.patientrecord, false, 0);
        id = getIntent().getIntExtra("id", 0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvRecord.setLayoutManager(linearLayoutManager);
        rvRecord.setAdapter(patientRecordsAdapter);

        View view = getLayoutInflater().inflate(R.layout.head_layout_record, null);
        tvName = view.findViewById(R.id.tvName);
        tvAge = view.findViewById(R.id.tvAge);
        tvPlace = view.findViewById(R.id.tvPlace);
        tvBirthPlace = view.findViewById(R.id.tvBirthPlace);
        tvNation = view.findViewById(R.id.tvNation);

        tvHistory1 = view.findViewById(R.id.tvHistory1);
        tvHistory2 = view.findViewById(R.id.tvHistory2);
        tvHistory3 = view.findViewById(R.id.tvHistory3);
        tvSmokingHistory = view.findViewById(R.id.tvSmokingHistory);
        tvDrink = view.findViewById(R.id.tvDrink);
        tvObstericalHistory = view.findViewById(R.id.tvObstericalHistory);
        tvFamilyHistory = view.findViewById(R.id.tvFamilyHistory);
        patientRecordsAdapter.addHeaderView(view);

        swipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadPatientRecordsData(id);

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void setPatientRecords(PatientRecordsBean patientRecordsBean, int loadType) {
        if (patientRecordsBean != null) {
            tvName.setText(getString(R.string.name) + patientRecordsBean.getName());
            tvPlace.setText(getString(R.string.address) + patientRecordsBean.getResidenceplace());
            tvAge.setText(getString(R.string.age) + String.valueOf(patientRecordsBean.getAge()));
            tvNation.setText(getString(R.string.nation) + patientRecordsBean.getNation());
            tvBirthPlace.setText(getString(R.string.birth_place) + patientRecordsBean.getBirthplace());

            tvHistory1.setText(TextUtils.equals("Y", patientRecordsBean.getIsHistory1()) ? "是" : TextUtils.equals("N", patientRecordsBean.getIsHistory1()) ? "否" : "未知");
            tvHistory2.setText(TextUtils.equals("Y", patientRecordsBean.getIsHistory2()) ? "是" : TextUtils.equals("N", patientRecordsBean.getIsHistory2()) ? "否" : "未知");
            tvHistory3.setText(TextUtils.equals("Y", patientRecordsBean.getIsHistory3()) ? "是" : TextUtils.equals("N", patientRecordsBean.getIsHistory3()) ? "否" : "未知");

            tvSmokingHistory.setText(TextUtils.isEmpty(patientRecordsBean.getSmokeHistory()) ? " " : patientRecordsBean.getSmokeHistory());
            tvDrink.setText(TextUtils.isEmpty(patientRecordsBean.getDrinkHistory()) ? " " : patientRecordsBean.getDrinkHistory());
            tvObstericalHistory.setText(TextUtils.isEmpty(patientRecordsBean.getMaritalHistory()) ? " " : patientRecordsBean.getMaritalHistory());
            tvFamilyHistory.setText(TextUtils.isEmpty(patientRecordsBean.getFamilyHistory()) ? " " : patientRecordsBean.getFamilyHistory());
            setLoadDataResult(patientRecordsAdapter, swipeRefreshLayout, patientRecordsBean.getCaseHistories(), loadType);
        }
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.loadPatientRecordsData(id);
    }
}
