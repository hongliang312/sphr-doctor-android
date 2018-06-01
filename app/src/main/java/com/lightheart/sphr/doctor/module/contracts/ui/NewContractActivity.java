package com.lightheart.sphr.doctor.module.contracts.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.contracts.adapter.ContractsAdapter;
import com.lightheart.sphr.doctor.module.contracts.contract.NewContractsContract;
import com.lightheart.sphr.doctor.module.contracts.presenter.NewContractPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-9.
 * Description :新的好友
 */

public class NewContractActivity extends BaseActivity<NewContractPresenter> implements NewContractsContract.View, ContractsAdapter.SlideItemListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvNewContracts)
    RecyclerView mRvNewContracts;
    @Inject
    ContractsAdapter mContractsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_contract;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.new_contract, false, 0);

        mContractsAdapter.initData(this, "APPLY");
        //  设置RecyclerView
        mRvNewContracts.setLayoutManager(new LinearLayoutManager(this));
        mRvNewContracts.setAdapter(mContractsAdapter);

        mContractsAdapter.setOnSlideItemListener(this);
        mContractsAdapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadNewContractData();
    }

    @Override
    public void setNewContracts(List<DoctorBean> contractDocList, int loadType) {
        setLoadDataResult(mContractsAdapter, mSwipeRefreshLayout, contractDocList, loadType);
        if (contractDocList != null && contractDocList.size() == 0)
            initEmptyView(mContractsAdapter, mRvNewContracts);
    }

    // 暂时不需要
    @Override
    public void itemClick(View view, int position, DoctorBean item) {
    }

    @Override
    public void accept(View view, int position, DoctorBean item) {
        DocContractRequestParams params = new DocContractRequestParams();
        params.status = "ADD";
        params.id = item.getId();
        assert mPresenter != null;
        mPresenter.operateDoc(params);
    }

    @Override
    public void deleteClick(View view, int position, DoctorBean item) {
        DocContractRequestParams params = new DocContractRequestParams();
        params.status = "REF";
        params.id = item.getId();
        assert mPresenter != null;
        mPresenter.operateDoc(params);
    }

    @Override
    public void onLoadMoreRequested() {
        assert mPresenter != null;
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
