package com.lightheart.sphr.doctor.module.home.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DetailsEntity;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.module.home.contract.TestDetailsContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestDetailsPresenter;

import java.util.List;

import butterknife.BindView;

public class TestDetailsActivity extends BaseActivity<TestDetailsPresenter> implements TestDetailsContract.View, View.OnClickListener{

    /*@BindView(R.id.recylview)
    RecyclerView recyclerView;*/
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
        String id = getIntent().getStringExtra("id");
        DetailsEntity entity=new DetailsEntity();
        entity.setDuid("8520");
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
            float v = ((float) detailslist.get(0).getInvolvedNum() / detailslist.get(0).getPlannedNum() * 100);
            Thregrouprate.setText(v+"%");
            State.setText(detailslist.get(0).getProjectStatusName());
            isStart.setText(detailslist.get(0).getIsStart());
            exitedNum.setText(detailslist.get(0).getExitedNum()+"");
            TotalplannedNum.setText(detailslist.get(0).getPlannedNum()+"");
            TotalinvolvedNum.setText(detailslist.get(0).getInvolvedNum()+"");
            TotalThegrouprate.setText(v+"%");
            TotalexitedNum.setText(detailslist.get(0).getExitedNum()+"");
        }
    }
}
