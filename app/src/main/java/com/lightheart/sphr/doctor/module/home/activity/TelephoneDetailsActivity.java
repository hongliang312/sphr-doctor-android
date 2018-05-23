package com.lightheart.sphr.doctor.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsBean;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsRequestParams;
import com.lightheart.sphr.doctor.module.home.adapter.TelephoneDetailsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.TelephoneDetailsContract;
import com.lightheart.sphr.doctor.module.home.presenter.TelephoneDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TelephoneDetailsActivity extends BaseActivity<TelephoneDetailsPresenter> implements TelephoneDetailsContract.View, View.OnClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.patientsname)
    TextView patientsname;
    @BindView(R.id.patientswithdisease)
    Button patientswithdisease;
    @BindView(R.id.conditiondescribe)
    TextView conditiondescribe;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.loadpicture)
    RecyclerView recyclerView;
    @BindView(R.id.linealayout)
    LinearLayout linealayout;
    @BindView(R.id.Linea)
    LinearLayout linea;
    @BindView(R.id.Layout)
    LinearLayout layout;
    @BindView(R.id.FeedBack)
    TextView feedback;
    @BindView(R.id.Submit)
    TextView submit;
    private TelephoneDetailsAdapter telephoneDetailsAdapter;
    private List<TelephoneDetailsBean.ImgsBean> contentt = new ArrayList<>();
    private String type="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_telephone_details;
    }

    @Override
    protected void initInjector() {
     mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        initToolbar(mToolbar,mTitleTv,mBtSub,R.string.telephonecounseling,false,0);
        String id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        TelephoneDetailsRequestParams telephondetails = new TelephoneDetailsRequestParams();
        telephondetails.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        telephondetails.id= Integer.valueOf(id);

        if("SER_CST_S_ING".equals(type)){
            linealayout.setVisibility(View.VISIBLE);
            linea.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
        }else {
            linealayout.setVisibility(View.GONE);
            linea.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
        }

        Log.i("type",""+type.toString());

        assert mPresenter != null;
        mPresenter.loadTelephoneDetailsData(telephondetails);

        patientswithdisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelephoneDetailsActivity.this, PatientRecordsActivity.class);
                intent.putExtra("id",contentt.get(0).getId()+"");
                startActivity(intent);
                Log.i("cccc",""+contentt.get(0).getId()+"");
                ToastUtils.showShort(R.string.tel_online);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.loadReplyConsultingData();
                feedback.setText(null);
            }
        });
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void setTelephoneDetails(TelephoneDetailsBean content) {

        if(content !=null){
            contentt.clear();
            contentt.addAll(content.getImgs());
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));
            telephoneDetailsAdapter = new TelephoneDetailsAdapter(this, contentt);
            recyclerView.setAdapter(telephoneDetailsAdapter);
            conditiondescribe.setText(content.getContent());
        }
    }

    @Override
    public void setReplyConsulting(String content) {

        ToastUtils.showShort("提交成功",content.toString());

    }


    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
