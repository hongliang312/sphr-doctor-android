package com.lightheart.sphr.doctor.module.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.CreatePanelDoctorParam;
import com.lightheart.sphr.doctor.bean.CreatePanelParam;
import com.lightheart.sphr.doctor.bean.DiseaseModel;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelGridAdapter;
import com.lightheart.sphr.doctor.module.home.contract.CreatePanelContract;
import com.lightheart.sphr.doctor.module.home.presenter.CreatePanelPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description : 创建专家组界面
 */

public class CreatePanelActivity extends BaseActivity<CreatePanelPresenter> implements CreatePanelContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mSubmit;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvPanels)
    RecyclerView mRvPanels;
    @Inject
    PanelGridAdapter mPanelGridAdapter;
    private List<DoctorBean> members = new ArrayList<>();
    private int mRequestCode = 1000;
    private TextInputEditText etPanelName;
    private TextView tvDisease;
    private List<DoctorBean> selectedItems;
    private List<String> disease = new ArrayList<>();
    private List<Integer> diseaseId = new ArrayList<>();
    private List<CreatePanelDoctorParam> doctorParamList = new ArrayList<>();
    private OptionsPickerView diseaseOption;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_panel;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mSubmit, R.string.create_panel, true, R.string.submit);
        mSubmit.setOnClickListener(this);

        // 设置header
        View header = getLayoutInflater().inflate(R.layout.header_create_panel, (ViewGroup) mRvPanels.getParent(), false);
        etPanelName = header.findViewById(R.id.etPanelName);
        LinearLayout llDiseaseSelect = header.findViewById(R.id.llDiseaseSelect);
        tvDisease = header.findViewById(R.id.tvDisease);
        llDiseaseSelect.setOnClickListener(this);
        mPanelGridAdapter.addHeaderView(header);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new GridLayoutManager(this, 5));
        mRvPanels.setAdapter(mPanelGridAdapter);
        mPanelGridAdapter.setOnItemClickListener(this);

        DoctorBean doctorBean = new DoctorBean();
        doctorBean.setContName("添加成员");
        members.add(0, doctorBean);
        mPanelGridAdapter.setNewData(members);

        mPanelGridAdapter.setType(0);// 0为创建专家组 1为专家组成员

        assert mPresenter != null;
        mPresenter.loadDiseaseData();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                checkContent();
                break;
            case R.id.llDiseaseSelect:
                if (diseaseOption != null) diseaseOption.show();
                break;
        }
    }

    private void checkContent() {
        String panelName = etPanelName.getText().toString().trim();
        String disease = tvDisease.getText().toString().trim();
        if (TextUtils.isEmpty(panelName)) {
            ToastUtils.showShort("请输入专家组名称");
            return;
        }
        if (TextUtils.isEmpty(disease)) {
            ToastUtils.showShort("请选择疾病");
            return;
        }
        if (doctorParamList != null && doctorParamList.size() == 0) {
            ToastUtils.showShort("请添加成员");
            return;
        }
        CreatePanelParam createPanelParam = new CreatePanelParam();
        createPanelParam.setCreateDuid(SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY));
        createPanelParam.setDtmAroName(panelName);
        createPanelParam.setDiagnosisArray(diseaseId);
        createPanelParam.setDoctorList(doctorParamList);

        assert mPresenter != null;
        mPresenter.createPanel(createPanelParam);
    }

    @Override
    public void createPanelSuccess() {
        ToastUtils.showShort("您已成功创建专家组!");
        this.finish();
    }

    @Override
    public void setDiseases(final List<DiseaseModel> diseaseModelList) {
        disease.clear();
        for (DiseaseModel diseaseModel : diseaseModelList) {
            disease.add(diseaseModel.diagnosisName);
        }
        // 医院选择器
        diseaseOption = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvDisease.setText(disease.get(options1));
                diseaseId.clear();
                diseaseId.add(diseaseModelList.get(options1).id);
            }
        }).build();
        diseaseOption.setNPicker(disease, null, null);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0) {
            startActivityForResult(new Intent(CreatePanelActivity.this, SelectContactActivity.class)
                    .putExtra("flag", "CREATE")
                    .putExtra("selectedItems", (Serializable) selectedItems), mRequestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == Activity.RESULT_OK) {
            members.clear();
            DoctorBean doctorBean = new DoctorBean();
            doctorBean.setContName("添加成员");
            members.add(0, doctorBean);
            selectedItems = (List<DoctorBean>) data.getSerializableExtra("selectedItems");
            members.addAll(selectedItems);
            mPanelGridAdapter.setNewData(members);
            for (DoctorBean doctor : selectedItems) {
                CreatePanelDoctorParam param = new CreatePanelDoctorParam();
                param.duid = doctor.getDuid();
                doctorParamList.add(param);
            }
        }
    }

}
