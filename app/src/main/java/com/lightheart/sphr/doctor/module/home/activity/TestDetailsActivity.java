package com.lightheart.sphr.doctor.module.home.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.module.home.contract.TestDetailsContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestDetailsPresenter;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;

public class TestDetailsActivity extends BaseActivity<TestDetailsPresenter> implements TestDetailsContract.View, View.OnClickListener{

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.numb)
    TextView numb;
    @BindView(R.id.Indication)
    TextView Indication;
    @BindView(R.id.COronary)
    TextView Cornary;
    @BindView(R.id.ProJectSponsor)
    TextView ProJectSponsor;
    @BindView(R.id.Projectorganizer)
    TextView Projectorganizer;
    @BindView(R.id.ProJectPI)
    TextView ProJectPI;

    @BindView(R.id.siteName)
    TextView siteName;
    @BindView(R.id.contacts)
    TextView contats;
    @BindView(R.id.plannedNum)
    TextView plannedNum;
    @BindView(R.id.involvedNum)
    TextView involvedNum;
    @BindView(R.id.Thegrouprate)
    TextView Thregrouprate;
    @BindView(R.id.State)
    TextView State;
    @BindView(R.id.isStart)
    TextView isStart;
    @BindView(R.id.exitedNum)
    TextView exitedNum;
    @BindView(R.id.TotalplannedNum)
    TextView TotalplannedNum;
    @BindView(R.id.TotalinvolvedNum)
    TextView TotalinvolvedNum;
    @BindView(R.id.TotalThegrouprate)
    TextView TotalThegrouprate;
    @BindView(R.id.TotalexitedNum)
    TextView TotalexitedNum;
    private List<TestDetails.CtrSiteAssignmentsBean> detailslist;

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
        initToolbar(mToolbar,mTitleTv,mBtSub,R.string.testingmanagement,false,0);
        String id = getIntent().getStringExtra("id");
        DetailsBean entity = new DetailsBean();
        entity.setDuid(id);
        entity.setId(id);

        assert mPresenter != null;
        mPresenter.loadDetailsData(entity);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setDetals(TestDetails content) {

        if(content != null){
            title.setText(TextUtils.isEmpty(content.getProjectName()) ? "" : content.getProjectName());
            time.setText(content.getTrialTime());
            numb.setText(content.getRecruitCount()+"");
            Indication.setText(content.getIndications()+"");
            Cornary.setText(content.getTrialPurpose());
            ProJectSponsor.setText(content.getOrganizeUnit());
            Projectorganizer.setText(content.getBidUnit());
            ProJectPI.setText(content.getTrialStage());
            detailslist = content.getCtrSiteAssignments();
            siteName.setText(detailslist.get(0).getSiteName());
            contats.setText(detailslist.get(0).getPiName());
            plannedNum.setText(detailslist.get(0).getPlannedNum()+"");
            involvedNum.setText(detailslist.get(0).getInvolvedNum()+"");

//            float v = ((float) detailslist.get(0).getInvolvedNum() / detailslist.get(0).getPlannedNum() * 100);
//            Thregrouprate.setText(v+"%");

            double div = div(detailslist.get(0).getInvolvedNum(), detailslist.get(0).getPlannedNum(), 4);
            Thregrouprate.setText(div*100+"%");
            State.setText(detailslist.get(0).getProjectStatusName());
            isStart.setText(detailslist.get(0).getIsStart());
            exitedNum.setText(detailslist.get(0).getExitedNum()+"");
            TotalplannedNum.setText(detailslist.get(0).getPlannedNum()+"");
            TotalinvolvedNum.setText(detailslist.get(0).getInvolvedNum()+"");
            TotalThegrouprate.setText(div*100+"%");
            TotalexitedNum.setText(detailslist.get(0).getExitedNum()+"");
        }
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
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
