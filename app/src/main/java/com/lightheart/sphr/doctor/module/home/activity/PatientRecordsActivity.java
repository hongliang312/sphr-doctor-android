package com.lightheart.sphr.doctor.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.module.home.adapter.PatientRecordsAdapter;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;
import com.lightheart.sphr.doctor.module.home.adapter.PatientRecordAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientRecordsContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientRecordsPresenter;

import javax.inject.Inject;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

public class PatientRecordsActivity extends BaseActivity<PatientRecordsPresenter> implements PatientRecordsContract.View, SwipeRefreshLayout.OnRefreshListener {
public class PatientRecordsActivity extends BaseActivity<PatientRecordsPresenter> implements PatientRecordsContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener{


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

    private int id;
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
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.patienrecycle)
    RecyclerView patienrecycle;
    @Inject
    PatientRecordAdapter patientRecordAdapter;

    private TextView patientname;
    private TextView address;
    private TextView age;
    private TextView nation;
    private TextView epidemic;
    private TextView epidemicarea;
    private TextView poison;
    private TextView smokinghistory;
    private TextView drinking;
    private TextView obstericalhistory;
    private TextView familyhistory;

    private List<PatientRecordsBean.CaseHistoriesBean> caseHistories;

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

        initToolbar(mToolbar,mTitleTv,mBtSub,R.string.patientrecord,false,0);
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.patientrecord, false, 0);
        id = getIntent().getIntExtra("id", 0);

        patienrecycle.setLayoutManager(new LinearLayoutManager(this));

        View patienRecodHead = LayoutInflater.from(this).inflate(R.layout.patientrecords,null);
        patientname = patienRecodHead.findViewById(R.id.patientname);
        address = patienRecodHead.findViewById(R.id.address);
        age = patienRecodHead.findViewById(R.id.age);
        nation = patienRecodHead.findViewById(R.id.nation);
        epidemic = patienRecodHead.findViewById(R.id.epidemic);
        epidemicarea = patienRecodHead.findViewById(R.id.epidemicarea);
        poison = patienRecodHead.findViewById(R.id.poison);
        smokinghistory = patienRecodHead.findViewById(R.id.smokinghistory);
        drinking = patienRecodHead.findViewById(R.id.drinking);
        obstericalhistory = patienRecodHead.findViewById(R.id.obstericalhistory);
        familyhistory = patienRecodHead.findViewById(R.id.familyhistory);

        patientRecordAdapter.addHeaderView(patienRecodHead);
        patienrecycle.setAdapter(patientRecordAdapter);


        String id = getIntent().getStringExtra("id");
        PatientRecordsRequestParams Params = new PatientRecordsRequestParams();
        Params.uid= 8532;//SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY)
        Params.id= Integer.valueOf(id);

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


    }

    @Override
    public void setPatientRecords(PatientRecordsBean content) {
       if(content!= null){
        caseHistories = content.getCaseHistories();
        patientname.setText(TextUtils.isEmpty(content.getName()) ? "" : content.getName());
        address.setText(TextUtils.isEmpty(content.getResidenceplace()) ? "" :content.getResidenceplace());
        age.setText(String.valueOf(content.getAge()));
        nation.setText(TextUtils.isEmpty(content.getNation()) ? "" :content.getNation());
        epidemic.setText(TextUtils.isEmpty(content.getIsHistory1()) ? "" :content.getIsHistory1());
        epidemicarea.setText(TextUtils.isEmpty(content.getIsHistory2()) ? "" :content.getIsHistory2());
        poison.setText(TextUtils.isEmpty(content.getIsHistory3()) ? "" :content.getIsHistory3());
        smokinghistory.setText(TextUtils.isEmpty(content.getSmokeHistory()) ? "" :content.getSmokeHistory());
        drinking.setText(TextUtils.isEmpty(content.getDrinkHistory()) ? "" :content.getDrinkHistory());
        obstericalhistory.setText(TextUtils.isEmpty(content.getMaritalHistory()) ? "" :content.getMaritalHistory());
        familyhistory.setText(TextUtils.isEmpty(content.getFamilyHistory()) ? "" :content.getFamilyHistory());

        patientRecordAdapter.setNewData(caseHistories);

       }
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
