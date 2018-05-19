package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.module.home.contract.ClinicalRecruitContract;
import com.lightheart.sphr.doctor.module.home.presenter.ClinicalRecruitPresenter;
import com.lightheart.sphr.doctor.module.my.ui.AuthenticationActivity;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-18.
 * Description : 临床试验招募详情页面
 */

public class ClinicalRecruitDetailActivity extends BaseActivity<ClinicalRecruitPresenter> implements ClinicalRecruitContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivStutas)
    ImageView ivStutas;
    @BindView(R.id.tvTrialTime)
    TextView tvTrialTime;
    @BindView(R.id.tvRecruitNum)
    TextView tvRecruitNum;
    @BindView(R.id.tvResearchTarget)
    TextView tvResearchTarget;
    @BindView(R.id.tvDocRightHint)
    TextView tvDocRightHint;
    @BindView(R.id.tvDocRight)
    TextView tvDocRight;
    @BindView(R.id.tvApplyHint)
    TextView tvApplyHint;
    @BindView(R.id.tvApply)
    TextView tvApply;
    @BindView(R.id.llPatientRights)
    LinearLayout llPatientRights;
    @BindView(R.id.tvInclusionCriteria)
    TextView tvInclusionCriteria;
    @BindView(R.id.tvExclusionCriteria)
    TextView tvExclusionCriteria;
    @BindView(R.id.tvTrialNum)
    TextView tvTrialNum;
    @BindView(R.id.tvStartEndTime)
    TextView tvStartEndTime;
    @BindView(R.id.tvSponsorName)
    TextView tvSponsorName;
    @BindView(R.id.tvFundOrigin)
    TextView tvFundOrigin;
    @BindView(R.id.tblCompanyIntroduce)
    TableLayout tblCompanyIntroduce;
    private int id;
    private int linkId;
    private String title;
    private String doctorName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clinical_recruit_detail;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initImageToolbar(mToolbar, mTitleTv, mIv, R.string.clinical_trial, true);
        mIv.setBackgroundResource(R.mipmap.ic_share);
        id = getIntent().getIntExtra("id", 0);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadClinicalRecruitDetail(id);
        mPresenter.loadDoctorInfo();

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void setClinicalRecruitDetail(HomePageInfo.ClinicalTrialListBean detail, int loadType) {
        if (detail != null) {
            linkId = detail.getId();
            title = detail.getProjectName();
            tvTitle.setText(TextUtils.isEmpty(detail.getProjectName()) ? "" : detail.getProjectName());
            if (TextUtils.equals("ING", detail.getRecruitStatus())) {
                ImageLoaderUtils.display(this, ivStutas, R.mipmap.ic_recruiting);
            } else {
                ImageLoaderUtils.display(this, ivStutas, R.mipmap.ic_recruit_over);
            }
            tvTrialTime.setText(TextUtils.isEmpty(detail.getTrialTime()) ? " " : detail.getTrialTime());
            tvRecruitNum.setText(String.valueOf(detail.getRecruitCount()));
            tvResearchTarget.setText(TextUtils.isEmpty(detail.getTrialPurpose()) ? " " : detail.getTrialPurpose());
            tvDocRight.setText(TextUtils.isEmpty(detail.getDoctorRights()) ? " " : detail.getDoctorRights());
            tvApply.setText(TextUtils.equals("ING", detail.getApplyStatus()) ? getString(R.string.applying)
                    : TextUtils.equals("SUC", detail.getApplyStatus()) ? getString(R.string.apply_success)
                    : TextUtils.equals("FAL", detail.getApplyStatus()) ? getString(R.string.apply_fail)
                    : getString(R.string.apply));
            llPatientRights.removeAllViews();
            if (!TextUtils.isEmpty(detail.getPatientRights())) {
                String[] split = detail.getPatientRights().trim().split("\\|");
                for (String aSplit : split) {
                    View itemRights = LayoutInflater.from(this).inflate(R.layout.item_patient_rights, null);
                    TextView tvPatientRights = itemRights.findViewById(R.id.tvPatientRights);
                    tvPatientRights.setText(aSplit);
                    llPatientRights.addView(itemRights);
                }
            }
            tvInclusionCriteria.setText(TextUtils.isEmpty(detail.getRecruitStandard()) ? " " : detail.getRecruitStandard());
            tvExclusionCriteria.setText(TextUtils.isEmpty(detail.getExcludeStandard()) ? " " : detail.getExcludeStandard());
            tvTrialNum.setText(TextUtils.isEmpty(detail.getTrialNum()) ? " " : detail.getTrialNum());
            tvStartEndTime.setText(TextUtils.isEmpty(detail.getTrialTime()) ? " " : detail.getTrialTime());
            tvSponsorName.setText(TextUtils.isEmpty(detail.getBidName()) ? " " : detail.getBidName());
            tvFundOrigin.setText(TextUtils.isEmpty(detail.getFundSource()) ? " " : detail.getFundSource());
            tblCompanyIntroduce.removeAllViews();
            if (detail.getCtrSiteList() != null && detail.getCtrSiteList().size() > 0) {
                for (int i = 0; i < detail.getCtrSiteList().size(); i++) {
                    HomePageInfo.CtrSite ctrSite = detail.getCtrSiteList().get(i);
                    View itemCompany = LayoutInflater.from(this).inflate(R.layout.item_company_introduce, null);
                    TableRow tbrCompany = itemCompany.findViewById(R.id.tbrCompany);
                    if (i % 2 == 1) {
                        tbrCompany.setBackgroundColor(getResources().getColor(R.color.tableRow));
                    } else {
                        tbrCompany.setBackgroundColor(getResources().getColor(R.color.colorDayBg));
                    }
                    TextView tvProjectName = itemCompany.findViewById(R.id.tvProjectName);
                    TextView tvStatus = itemCompany.findViewById(R.id.tvStatus);
                    TextView tvContractName = itemCompany.findViewById(R.id.tvContractName);
                    TextView tvContractWay = itemCompany.findViewById(R.id.tvContractWay);
                    tvProjectName.setText(TextUtils.isEmpty(ctrSite.getSiteName()) ? " " : ctrSite.getSiteName());
                    if (TextUtils.equals("ING", ctrSite.getProjectStatus())) {
                        tvStatus.setText("正在招募");
                        tvStatus.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        tvStatus.setText("招募完成");
                        tvStatus.setTextColor(getResources().getColor(R.color.title_black));
                    }
                    tvContractName.setText(TextUtils.isEmpty(ctrSite.getContacts()) ? " " : ctrSite.getContacts());
                    tvContractWay.setText(TextUtils.isEmpty(ctrSite.getContactWay()) ? " " : ctrSite.getContactWay());
                    tblCompanyIntroduce.addView(itemCompany);
                }
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    // 暂时不用
    @Override
    public void setClinical(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialListBeanList, int loadType) {
    }

    @Override
    public void setDoctorInfo(DoctorBean docInfo) {
        tvDocRightHint.setText(transformString(R.string.doctor_rights_hint, 15, 19, R.color.theme_color));
        tvApplyHint.setText(transformString(R.string.doctor_accept_hint, 17, 21, R.color.theme_color));
        if (docInfo != null) {
            doctorName = docInfo.getRealName();
            if (TextUtils.equals("USR_CERT_S_SUC", docInfo.getCertStatus())) {
                tvDocRight.setVisibility(View.VISIBLE);
                tvDocRightHint.setVisibility(View.GONE);
                tvApply.setVisibility(View.VISIBLE);
                tvApplyHint.setVisibility(View.GONE);
            } else {
                tvDocRight.setVisibility(View.GONE);
                tvDocRightHint.setVisibility(View.VISIBLE);
                tvApplyHint.setVisibility(View.VISIBLE);
                tvApply.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.iv, R.id.tvDocRightHint, R.id.tvApplyHint, R.id.tvApply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                // 分享到参与的专家组
                startActivity(new Intent(ClinicalRecruitDetailActivity.this, PanelListActivity.class)
                        .putExtra("flag", "Y")
                        .putExtra("TODO", "SHARE")
                        .putExtra("linkId", linkId)
                        .putExtra("shareTitle", title)
                        .putExtra("sharerName", doctorName));
                break;
            case R.id.tvDocRightHint:
            case R.id.tvApplyHint:
                // 去认证
                startActivity(new Intent(ClinicalRecruitDetailActivity.this, AuthenticationActivity.class));
                break;
            case R.id.tvApply:
                // 申请参与
                assert mPresenter != null;
                mPresenter.applyClinicalRecruit(id);
                break;
        }
    }

    @Override
    public void successApply() {
        ToastUtils.showShort(getString(R.string.apply_success));
        assert mPresenter != null;
        mPresenter.loadClinicalRecruitDetail(id);
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.loadClinicalRecruitDetail(id);
        mPresenter.loadDoctorInfo();
    }

    private SpannableString transformString(int s, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(getString(s));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(color));
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
