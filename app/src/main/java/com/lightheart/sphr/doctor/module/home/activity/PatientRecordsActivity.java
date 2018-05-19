package com.lightheart.sphr.doctor.module.home.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;
import com.lightheart.sphr.doctor.module.home.adapter.PatientRecordsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientRecordsContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientRecordsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PatientRecordsActivity extends BaseActivity<PatientRecordsPresenter> implements PatientRecordsContract.View, View.OnClickListener {


   @BindView(R.id.common_toolbar)
   Toolbar mToolbar;
   @BindView(R.id.bt_sub)
   Button mBtSub;
   @BindView(R.id.common_toolbar_title_tv)
   TextView mTitleTv;
    @BindView(R.id.listview)
    ListView listView;
    private PatientRecordsAdapter patientRecordsAdapter;
    private View view;
    private List<PatientRecordsBean.CaseHistoriesBean> list = new ArrayList<>();
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
        view = View.inflate(this, R.layout.patientrecords, null);
        patientname = view.findViewById(R.id.patientname);
        address = view.findViewById(R.id.address);
        age = view.findViewById(R.id.age);
        nation = view.findViewById(R.id.nation);
        epidemic = view.findViewById(R.id.epidemic);
        epidemicarea = view.findViewById(R.id.epidemicarea);
        poison = view.findViewById(R.id.poison);
        smokinghistory = view.findViewById(R.id.smokinghistory);
        drinking = view.findViewById(R.id.drinking);
        obstericalhistory = view.findViewById(R.id.obstericalhistory);
        familyhistory = view.findViewById(R.id.familyhistory);

        listView.addHeaderView(view);
        String id = getIntent().getStringExtra("id");
        PatientRecordsRequestParams Params = new PatientRecordsRequestParams();
        Params.uid= SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        Params.id= Integer.valueOf(id);
        assert mPresenter != null;
        mPresenter.loadPatientRecordsData(Params);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPatientRecords(PatientRecordsBean content) {

        if(content!= null){
        list.clear();
        list.addAll(content.getCaseHistories());
         patientname.setText(content.getName());
        address.setText(content.getResidenceplace());
        age.setText(content.getAge()+"");
        nation.setText(content.getNation());
        epidemic.setText(content.getIsHistory1());
        epidemicarea.setText(content.getIsHistory2());
        poison.setText(content.getIsHistory3());
        smokinghistory.setText(content.getSmokeHistory());
        drinking.setText(content.getDrinkHistory());
        obstericalhistory.setText(content.getMaritalHistory());
        familyhistory.setText(content.getFamilyHistory());

        patientRecordsAdapter = new PatientRecordsAdapter(this,list);
        listView.setAdapter(patientRecordsAdapter);

          }
    }
    @Override
    protected boolean showHomeAsUp() {

        return true;
    }
}
