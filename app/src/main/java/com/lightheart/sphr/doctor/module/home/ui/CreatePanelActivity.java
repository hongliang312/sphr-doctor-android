package com.lightheart.sphr.doctor.module.home.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.CreatePanelParam;
import com.lightheart.sphr.doctor.bean.DiseaseModel;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.home.adapter.PanelGridAdapter;
import com.lightheart.sphr.doctor.module.home.contract.CreatePanelContract;
import com.lightheart.sphr.doctor.module.home.presenter.CreatePanelPresenter;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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
        initToolbar(mToolbar, mTitleTv, mSubmit, R.string.panel_manage, true, R.string.submit);
        mSubmit.setOnClickListener(this);

        // 设置header
        View header = getLayoutInflater().inflate(R.layout.header_create_panel, (ViewGroup) mRvPanels.getParent(), false);
        TextInputEditText etPanelName = header.findViewById(R.id.etPanelName);
        LinearLayout llDiseaseSelect = header.findViewById(R.id.llDiseaseSelect);
        TextView tvDisease = header.findViewById(R.id.tvDisease);
        llDiseaseSelect.setOnClickListener(this);
        mPanelGridAdapter.addHeaderView(header);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new GridLayoutManager(this, 5));
        mRvPanels.setAdapter(mPanelGridAdapter);
        mPanelGridAdapter.setOnItemClickListener(this);

        DoctorBean doctorBean = new DoctorBean();
        doctorBean.setDoctorName("添加成员");
        members.add(0, doctorBean);
        mPanelGridAdapter.setNewData(members);

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
//                CreatePanelParam param = new CreatePanelParam();
                ToastUtils.showShort(getString(R.string.submit));
                break;
            case R.id.llDiseaseSelect:
                ToastUtils.showShort(getString(R.string.disease_select));
                break;
        }
    }

    @Override
    public void createPanelSuccess() {

    }

    @Override
    public void setDiseases(List<DiseaseModel> diseaseModelList) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0) {
            startActivityForResult(new Intent(CreatePanelActivity.this, SelectContactActivity.class).putExtra("flag", "CREATE"), mRequestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && resultCode == Activity.RESULT_OK) {
            List<DoctorBean> selectedItems = (List<DoctorBean>) data.getSerializableExtra("selectedItems");
            members.addAll(selectedItems);
            mPanelGridAdapter.setNewData(members);
        }
    }
}
