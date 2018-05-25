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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.Invite2PanelParam;
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
    private List<Invite2PanelParam.InviteDoctor> inviteList = new ArrayList<>();
    private String mFlag;
    private int dtmAroId;

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
        mFlag = getIntent().getStringExtra("flag");// INVITE邀请联系人进专家组
        List<DoctorBean> selectedItems = (List<DoctorBean>) getIntent().getSerializableExtra("selectedItems");
        dtmAroId = getIntent().getIntExtra("dtmAroId", 0);
        if (TextUtils.equals("CREATE", mFlag)) {
            initToolbar(mToolbar, mTitleTv, mSubmit, R.string.contract_select, true, R.string.complete);
            if (selectedItems != null) {
                selectedContract.addAll(selectedItems);
            }
        } else if (TextUtils.equals("INVITE", mFlag)) {
            initToolbar(mToolbar, mTitleTv, mSubmit, R.string.contract_select, true, R.string.invite);
            if (selectedItems != null) {
                for (DoctorBean doctorBean : selectedItems) {
                    if (!TextUtils.equals("添加成员", doctorBean.getDoctorName())) {
                        selectedContract.add(doctorBean);
                    }
                }
            }
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
        if (TextUtils.equals("CREATE", mFlag)) {
            setResult(Activity.RESULT_OK, getIntent().putExtra("selectedItems", (Serializable) selectedContract));
            this.finish();
        } else if (TextUtils.equals("INVITE", mFlag)) {
            if (inviteList.size() > 0) {
                Invite2PanelParam invite2PanelParam = new Invite2PanelParam();
                invite2PanelParam.dtmAroId = dtmAroId;
                invite2PanelParam.inviteId = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
                invite2PanelParam.contactList = inviteList;
                assert mPresenter != null;
                mPresenter.invite2Panel(invite2PanelParam);
            } else ToastUtils.showShort(getString(R.string.please_select_contract));
        }
    }

    @Override
    public void setContracts(List<DoctorBean> contractDocList, int loadType) {
        if (selectedContract != null) {
            if (contractDocList != null) {
                for (int j = 0; j < contractDocList.size(); j++) {
                    DoctorBean doctor = contractDocList.get(j);
                    for (int i = 0; i < selectedContract.size(); i++) {
                        DoctorBean selectItem = selectedContract.get(i);
                        if (TextUtils.equals("CREATE", mFlag)) {
                            if (doctor.getContUid() == selectItem.getContUid()) {
                                doctor.isCheck = true;
                                break;
                            }
                        } else if (TextUtils.equals("INVITE", mFlag)) {
                            doctor.isEnable = doctor.getContUid() != selectItem.getDuid();
                        }
                    }
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

    // 暂时不用
    @Override
    public void setDocInfo(DoctorBean docInfo) {
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DoctorBean doctorBean = (DoctorBean) adapter.getItem(position);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbContract);
        assert doctorBean != null;
        if (TextUtils.equals("CREATE", mFlag)) {
            checkBox.toggle();
            doctorBean.isCheck = checkBox.isChecked();
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
        } else if (TextUtils.equals("INVITE", mFlag)) {// 邀请医生进专家组
            if (doctorBean.isEnable) {
                checkBox.toggle();
                doctorBean.isCheck = checkBox.isChecked();
                mAdapter.getIsSelected().put(position - 1, checkBox.isChecked());
                if (checkBox.isChecked()) {
                    Invite2PanelParam.InviteDoctor inviteDoctor = new Invite2PanelParam.InviteDoctor();
                    inviteDoctor.contUid = doctorBean.getContUid();
                    inviteDoctor.contName = doctorBean.getContName();
                    inviteList.add(inviteDoctor);
                } else {
                    for (int i = 0; i < inviteList.size(); i++) {
                        if (inviteList.get(i).contUid == doctorBean.getContUid()) {
                            inviteList.remove(i);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
