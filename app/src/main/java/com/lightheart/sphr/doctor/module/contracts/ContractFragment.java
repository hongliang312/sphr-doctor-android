package com.lightheart.sphr.doctor.module.contracts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.QRcodeModel;
import com.lightheart.sphr.doctor.module.contracts.adapter.ContractsAdapter;
import com.lightheart.sphr.doctor.module.contracts.contract.ContractsContract;
import com.lightheart.sphr.doctor.module.contracts.presenter.ContractPresenter;
import com.lightheart.sphr.doctor.module.contracts.ui.NewContractActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.ScanActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.SearchPhoneActivity;
import com.lightheart.sphr.doctor.module.my.ui.AuthenticationActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyInvitationCodeActivity;
import com.lightheart.sphr.doctor.utils.RxBus;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.lightheart.sphr.doctor.app.Constant.RC_CAMERA_PERM;

/**
 * Created by fucp on 2018-4-19.
 * Description :联系人页面
 */

public class ContractFragment extends BaseFragment<ContractPresenter> implements ContractsContract.View, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ContractsAdapter.SlideItemListener, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.mainBar)
    AppBarLayout mainBar;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvContracts)
    RecyclerView mRvContracts;
    @Inject
    ContractsAdapter mContractsAdapter;
    private PopupWindow mAddPop;
    private DoctorBean doctorBean = new DoctorBean();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.title_contract, true, R.string.add);
        mBtSub.setOnClickListener(this);

        mContractsAdapter.initData(getActivity(), "ADDED");
        //  设置RecyclerView
        mRvContracts.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvContracts.setAdapter(mContractsAdapter);

        //   设置ContractView
        View mContractHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_contract_head, null);
        TextView tvNewCon = mContractHeadView.findViewById(R.id.tvNewCon);
        tvNewCon.setOnClickListener(this);

        mContractsAdapter.addHeaderView(mContractHeadView);

        mContractsAdapter.setOnSlideItemListener(this);
        mContractsAdapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadContractData();
        mPresenter.loadDocData();
    }

    public static ContractFragment newInstance() {
        return new ContractFragment();
    }

    @Override
    public void setContracts(List<DoctorBean> contractDocList, int loadType) {
        setLoadDataResult(mContractsAdapter, mSwipeRefreshLayout, contractDocList, loadType);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                showAdd();
                break;
            case R.id.tvNewCon:
                startActivity(new Intent(getActivity(), NewContractActivity.class));
                break;
            case R.id.tvAddByPhone:
                startActivity(new Intent(getActivity(), SearchPhoneActivity.class));
                if (mAddPop != null) mAddPop.dismiss();
                break;
            case R.id.tvScan:
                cameraTask();
                if (mAddPop != null) mAddPop.dismiss();
                break;
            case R.id.tvQrCode:
                if (TextUtils.equals("USR_CERT_S_UN", doctorBean.getCertStatus()) || TextUtils.equals("USR_CERT_S_FAL", doctorBean.getCertStatus())) {
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MyInvitationCodeActivity.class).putExtra("docInfo", doctorBean));
                }
                if (mAddPop != null) mAddPop.dismiss();
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (hasCameraPermission()) {
            startActivityForResult(new Intent(getActivity(), ScanActivity.class), Activity.RESULT_FIRST_USER);
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_camera),
                    RC_CAMERA_PERM,
                    Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER && resultCode == Activity.RESULT_OK && data != null) {
            QRcodeModel info = (QRcodeModel) data.getSerializableExtra("info");
            startActivity(new Intent(getActivity(), MyHomePageActivity.class).putExtra("duid", info.id));
        }
    }

    private void showAdd() {
        View view = getLayoutInflater().inflate(R.layout.add_contract_layout, null, false);
        TextView mTvAddByPhone = view.findViewById(R.id.tvAddByPhone);
        TextView mTvScan = view.findViewById(R.id.tvScan);
        TextView mTvQrCode = view.findViewById(R.id.tvQrCode);
        mAddPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mAddPop.setAnimationStyle(R.style.addPopAnim);
        mAddPop.setFocusable(true);
        mAddPop.setOutsideTouchable(true);
        mAddPop.setTouchable(true);
        mAddPop.showAtLocation(mainBar, Gravity.TOP | Gravity.END, SizeUtils.dp2px(14), mainBar.getHeight() + SizeUtils.dp2px(4));
        mTvAddByPhone.setOnClickListener(this);
        mTvScan.setOnClickListener(this);
        mTvQrCode.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showFaild(String errorMsg) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        assert mPresenter != null;
        mPresenter.loadMore();
    }

    @Override
    public void itemClick(View view, int position, DoctorBean item) {
        assert item != null;
        startActivity(new Intent(getActivity(), MyHomePageActivity.class).putExtra("duid", item.getContUid()));
    }

    // 暂时不需要
    @Override
    public void accept(View view, int position, DoctorBean item) {

    }

    // 暂时不需要
    @Override
    public void successInvite() {

    }

    @Override
    public void setDocInfo(DoctorBean docInfo) {
        doctorBean = docInfo;
    }

    @Override
    public void deleteClick(View view, int position, DoctorBean item) {
        DocContractRequestParams params = new DocContractRequestParams();
        params.status = "DEL";
        params.id = item.getId();
        assert mPresenter != null;
        mPresenter.deleteDoc(params);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


}
