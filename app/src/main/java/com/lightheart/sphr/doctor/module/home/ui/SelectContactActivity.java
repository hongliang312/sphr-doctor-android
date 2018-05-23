package com.lightheart.sphr.doctor.module.home.ui;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.contract.ContractsContract;
import com.lightheart.sphr.doctor.module.contracts.presenter.ContractPresenter;
import com.lightheart.sphr.doctor.module.home.adapter.SelectContractAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-16.
 * Description : 选择联系人页面
 */

public class SelectContactActivity extends BaseActivity<ContractPresenter> implements ContractsContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mSubmit;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvContract)
    RecyclerView rvContract;
    @Inject
    SelectContractAdapter mAdapter;
    private List<DoctorBean> selectedContract = new ArrayList<>();// 被选中的条目集合
    private String mFlag;
    private List<DoctorBean> slectedItems;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_contract;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mFlag = getIntent().getStringExtra("flag");
        slectedItems = (List<DoctorBean>) getIntent().getSerializableExtra("slectedItems");
        if (TextUtils.equals("CREATE", mFlag)) {
            initToolbar(mToolbar, mTitleTv, mSubmit, R.string.contract_select, true, R.string.complete);
        } else if (TextUtils.equals("INVITE", mFlag)) {
            initToolbar(mToolbar, mTitleTv, mSubmit, R.string.contract_select, true, R.string.invite);
        }

        if (slectedItems != null) {
            selectedContract.addAll(slectedItems);
        }

        //  设置RecyclerView
        rvContract.setLayoutManager(new LinearLayoutManager(this));
        rvContract.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setEnabled(false);

        assert mPresenter != null;
        mPresenter.loadContractData();
    }

    @OnClick(R.id.bt_sub)
    public void onClick() {
        if (selectedContract.size() > 0) {
            if (TextUtils.equals("CREATE", mFlag)) {
                setResult(Activity.RESULT_OK, getIntent().putExtra("selectedItems", (Serializable) selectedContract));
                this.finish();
            } else if (TextUtils.equals("INVITE", mFlag)) {
                //TODO 邀请加入专家组，待完成
                assert mPresenter != null;
                mPresenter.invite2Panel();
            }
        } else ToastUtils.showShort(getString(R.string.please_select_contract));
    }

    @Override
    public void setContracts(List<DoctorBean> contractDocList, int loadType) {
        if (slectedItems != null) {
            for (DoctorBean selectitem : slectedItems) {
                for (DoctorBean doctor : contractDocList) {
                    doctor.setCheck(doctor.getContUid() == selectitem.getContUid());
                }
            }
        }
        setLoadDataResult(mAdapter, mSwipeRefreshLayout, contractDocList, loadType);
        mAdapter.initData(contractDocList);
    }

    @Override
    public void successInvite() {
        this.finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DoctorBean doctorBean = (DoctorBean) adapter.getItem(position);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbContract);
        checkBox.toggle();
        assert doctorBean != null;
        doctorBean.setCheck(checkBox.isChecked());
        mAdapter.getIsSelected().put(position - 1, checkBox.isChecked());
        if (checkBox.isChecked()) {
            selectedContract.add(doctorBean);
        } else {
            for (int i = 0; i < selectedContract.size(); i++) {
                if (selectedContract.get(i).getContUid() == doctorBean.getContUid()) {
                    selectedContract.remove(i);
                }
            }
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
