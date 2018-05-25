package com.lightheart.sphr.doctor.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.module.home.adapter.TestDetailsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.TestDetailsContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestDetailsPresenter;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;

public class TestDetailsActivity extends BaseActivity<TestDetailsPresenter> implements TestDetailsContract.View{

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
    @BindView(R.id.tvResearchTarget)
    TextView research;
    @BindView(R.id.ProJectSponsor)
    TextView ProJectSponsor;
    @BindView(R.id.Projectorganizer)
    TextView Projectorganizer;
    @BindView(R.id.ProJectPI)
    TextView ProJectPI;
    @BindView(R.id.TotalplannedNum)
    TextView TotalplannedNum;
    @BindView(R.id.TotalinvolvedNum)
    TextView TotalinvolvedNum;
    @BindView(R.id.TotalThegrouprate)
    TextView TotalThegrouprate;
    @BindView(R.id.TotalexitedNum)
    TextView TotalexitedNum;
    @BindView(R.id.testrecycler)
    RecyclerView testrecycler;
    @Inject
    TestDetailsAdapter testDetailsAdapter;
    private List<TestDetails.CtrSiteAssignmentsBean> detailslist;
    int TotalplannedSum,TotalinvolvedSum,TotalThegrouprateSum,TotalexitedSum;
    private int id;


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

        testrecycler.setLayoutManager(new LinearLayoutManager(this));
        testrecycler.setAdapter(testDetailsAdapter);

        id =getIntent().getIntExtra("id",0);

        assert mPresenter != null;
        mPresenter.loadDetailsData(id);

    }
    @Override
    public void setDetals(TestDetails content) {

        if(content != null){
            detailslist = content.getCtrSiteAssignments();
            title.setText(TextUtils.isEmpty(content.getProjectName()) ? "" : content.getProjectName());
            time.setText(content.getTrialTime());
            numb.setText(String.valueOf(content.getRecruitCount()+"人"));
            Indication.setText(String.valueOf(content.getIndications()+""));
            research.setText(content.getTrialPurpose());
            ProJectSponsor.setText(content.getOrganizeUnit());
            Projectorganizer.setText(content.getBidUnit());
            ProJectPI.setText(content.getTrialStage());

            testDetailsAdapter.setNewData(detailslist);

            if(detailslist!=null){

                for (int i=0;i<detailslist.size();i++){
                    TotalplannedSum+=detailslist.get(i).getPlannedNum();
                    TotalinvolvedSum+=detailslist.get(i).getInvolvedNum();
                    float v = (float) (div(detailslist.get(i).getInvolvedNum(),detailslist.get(i).getPlannedNum(), 2))*100;
                    TotalThegrouprateSum+=v;
                    TotalexitedSum+=detailslist.get(i).getExitedNum();
                }

                TotalplannedNum.setText(String.valueOf(TotalplannedSum+""));
                TotalinvolvedNum.setText(String.valueOf(TotalinvolvedSum+""));
                TotalThegrouprate.setText(String.valueOf(TotalThegrouprateSum+"%"));
                TotalexitedNum.setText(String.valueOf(TotalexitedSum+""));

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
