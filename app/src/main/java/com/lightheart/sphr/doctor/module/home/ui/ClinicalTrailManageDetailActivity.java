package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.module.home.contract.TestDetailsContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestDetailsPresenter;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;

public class ClinicalTrailManageDetailActivity extends BaseActivity<TestDetailsPresenter> implements TestDetailsContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.numb)
    TextView numb;
    @BindView(R.id.Indication)
    TextView Indication;
    @BindView(R.id.tvResearchTarget)
    TextView research;
    @BindView(R.id.ProJectSponsor)
    TextView ProJectSponsor;
    @BindView(R.id.Projectorganizer)
    TextView Projectorganizer;
    @BindView(R.id.ProJectPI)
    TextView ProJectPI;
    @BindView(R.id.tvTotalPlanNum)
    TextView tvTotalPlanNum;
    @BindView(R.id.tvTotalJoinNum)
    TextView tvTotalJoinNum;
    @BindView(R.id.tvTotalRate)
    TextView tvTotalRate;
    @BindView(R.id.tvTotalExitNum)
    TextView tvTotalExitNum;

    @BindView(R.id.tblCompanyIntroduce)
    TableLayout tblCompanyIntroduce;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.testingmanagement, false, 0);

        int id = getIntent().getIntExtra("id", 0);

        assert mPresenter != null;
        mPresenter.loadDetailsData(id);

    }

    @Override
    public void setDetals(TestDetails content) {

        if (content != null) {
            List<TestDetails.CtrSiteAssignmentsBean> detailslist = content.getCtrSiteAssignments();
            tvTitle.setText(TextUtils.isEmpty(content.getProjectName()) ? "" : content.getProjectName());
            time.setText(content.getTrialTime());
            numb.setText(String.valueOf(content.getRecruitCount() + "人"));
            Indication.setText(String.valueOf(content.getIndications() + ""));
            research.setText(content.getTrialPurpose());
            ProJectSponsor.setText(content.getOrganizeUnit());
            Projectorganizer.setText(content.getBidUnit());
            ProJectPI.setText(content.getTrialStage());

            double totalPlan = 0;
            double totalJoin = 0;
            double totalRate = 0;
            double totalExit = 0;
            if (detailslist != null && detailslist.size() > 0) {
                for (int i = 0; i < detailslist.size(); i++) {
                    TestDetails.CtrSiteAssignmentsBean ctrSite = detailslist.get(i);

                    View itemCompany = LayoutInflater.from(this).inflate(R.layout.tabrow_project_item, null);

                    TextView tvSite = itemCompany.findViewById(R.id.tvSite);
                    TextView tvPiName = itemCompany.findViewById(R.id.tvPiName);
                    TextView tvSearchName = itemCompany.findViewById(R.id.tvSearchName);
                    TextView tvPlanNum = itemCompany.findViewById(R.id.tvPlanNum);
                    TextView tvJoinNum = itemCompany.findViewById(R.id.tvJoinNum);
                    TextView tvGroupRate = itemCompany.findViewById(R.id.tvGroupRate);
                    TextView tvStatus = itemCompany.findViewById(R.id.tvStatus);
                    TextView tvIsStart = itemCompany.findViewById(R.id.tvIsStart);
                    TextView tvExitNum = itemCompany.findViewById(R.id.tvExitNum);

                    tvSite.setText(TextUtils.isEmpty(ctrSite.getSiteName()) ? " " : ctrSite.getSiteName());
                    tvPiName.setText(TextUtils.isEmpty(ctrSite.getPiName()) ? " " : ctrSite.getPiName());
                    tvSearchName.setText(TextUtils.isEmpty(ctrSite.getResearcher()) ? " " : ctrSite.getResearcher());

                    tvPlanNum.setText(String.valueOf(ctrSite.getPlannedNum()));
                    totalPlan += ctrSite.getPlannedNum();
                    tvJoinNum.setText(String.valueOf(ctrSite.getInvolvedNum()));
                    totalJoin += ctrSite.getInvolvedNum();
                    double rate = div(ctrSite.getInvolvedNum(), ctrSite.getPlannedNum(), 2) * 100;
                    totalRate += rate;
                    tvGroupRate.setText(String.valueOf(rate) + "%");

                    tvStatus.setText(TextUtils.isEmpty(ctrSite.getProjectStatusName()) ? "未知" : ctrSite.getProjectStatusName());
                    tvIsStart.setText(TextUtils.equals("Y", ctrSite.getIsStart()) ? "是" :
                            TextUtils.equals("N", ctrSite.getIsStart()) ? "否" : "未知");
                    tvExitNum.setText(String.valueOf(ctrSite.getExitedNum()));
                    totalExit += ctrSite.getExitedNum();

                    tblCompanyIntroduce.addView(itemCompany);
                }
                tvTotalPlanNum.setText(String.valueOf(totalPlan));
                tvTotalJoinNum.setText(String.valueOf(totalJoin));
                tvTotalRate.setText(String.valueOf(totalRate) + "%");
                tvTotalExitNum.setText(String.valueOf(totalExit));
            }
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }


    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
