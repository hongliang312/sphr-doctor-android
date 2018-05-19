package com.lightheart.sphr.doctor.module.my.ui;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-19.
 * Description : 个人资料页面
 */

public class MyPersonalInfoActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.llHeaderImage)
    LinearLayout llHeaderImage;
    @BindView(R.id.clvHeadImage)
    CircleImageView clvHeadImage;
    @BindView(R.id.etName)
    TextInputEditText etName;
    @BindView(R.id.etSex)
    TextInputEditText etSex;
    @BindView(R.id.etBirth)
    TextInputEditText etBirth;
    @BindView(R.id.etDistract)
    TextInputEditText etDistract;
    @BindView(R.id.etHospital)
    TextInputEditText etHospital;
    @BindView(R.id.etDepartment)
    TextInputEditText etDepartment;
    @BindView(R.id.etTitle)
    TextInputEditText etTitle;
    @BindView(R.id.etMajor)
    TextInputEditText etMajor;
    @BindView(R.id.etPersonalIntroduce)
    TextInputEditText etPersonalIntroduce;
    @BindView(R.id.etOutsideNum)
    TextInputEditText etOutsideNum;
    @BindView(R.id.etOperationNum)
    TextInputEditText etOperationNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.personal_info, true, R.string.complete);

        DoctorBean info = (DoctorBean) getIntent().getSerializableExtra("info");
        if (info != null) {
            ImageLoaderUtils.display(this, clvHeadImage, info.getImgUrl());
            etName.setText(TextUtils.isEmpty(info.getRealName()) ? "" : info.getRealName());
            etSex.setText(TextUtils.isEmpty(info.getSex()) ? "" : info.getSex());
            etBirth.setText(info.getBirth() == 0 ? "" : TimeUtils.millis2String(info.getBirth(), new SimpleDateFormat("yyyy-MM-DD")));
            etDistract.setText(TextUtils.isEmpty(info.getDistrictName()) ? "" : info.getDistrictName());
            etHospital.setText(TextUtils.isEmpty(info.getHospitalName()) ? "" : info.getHospitalName());
            etDepartment.setText(TextUtils.isEmpty(info.getDepartmentName()) ? "" : info.getDepartmentName());
            etTitle.setText(TextUtils.isEmpty(info.getTitleName()) ? "" : info.getTitleName());
            etMajor.setText(TextUtils.isEmpty(info.getMajorIn()) ? "" : info.getMajorIn());
            etPersonalIntroduce.setText(TextUtils.isEmpty(info.getSummary()) ? "" : info.getSummary());
            etOutsideNum.setText(info.getOutpatientNum() == 0 ? "0" : String.valueOf(info.getOutpatientNum()));
            etOperationNum.setText(info.getOperationNum() == 0 ? "0" : String.valueOf(info.getOperationNum()));

        }

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
