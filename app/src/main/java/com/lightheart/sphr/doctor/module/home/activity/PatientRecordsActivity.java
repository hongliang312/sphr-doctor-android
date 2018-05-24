package com.lightheart.sphr.doctor.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;
import com.lightheart.sphr.doctor.module.home.adapter.PatientRecordAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientRecordsContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientRecordsPresenter;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
public class PatientRecordsActivity extends BaseActivity<PatientRecordsPresenter> implements PatientRecordsContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener{


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

        assert mPresenter != null;
        mPresenter.loadPatientRecordsData(Params);


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
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
