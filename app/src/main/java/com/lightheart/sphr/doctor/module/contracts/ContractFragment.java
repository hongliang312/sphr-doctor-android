package com.lightheart.sphr.doctor.module.contracts;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.module.contracts.adapter.ContractsAdapter;
import com.lightheart.sphr.doctor.module.contracts.contract.ContractsContract;
import com.lightheart.sphr.doctor.module.contracts.presenter.ContractPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-4-19.
 * Description :联系人页面
 */

public class ContractFragment extends BaseFragment<ContractPresenter> implements ContractsContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

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
    private ContractsAdapter mContractsAdapter;

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

        //  设置RecyclerView
        mRvContracts.setLayoutManager(new LinearLayoutManager(getContext()));
        mContractsAdapter = new ContractsAdapter(getActivity(), R.layout.item_doc_contract);
        mRvContracts.setAdapter(mContractsAdapter);

        // 设置ContractView
        View mContractHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_contract_head, null);
        TextView tvNewCon = mContractHeadView.findViewById(R.id.tvNewCon);
        tvNewCon.setOnClickListener(this);

        mContractsAdapter.addHeaderView(mContractHeadView);

        mContractsAdapter.setOnItemClickListener(this);
        mContractsAdapter.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        DocContractRequestParams params = new DocContractRequestParams();
        params.duid = 8520;
        params.pageNum = 1;
        params.pageSize = 30;
        params.status = "ADD";
        mPresenter.loadContractData();
    }

    public static ContractFragment newInstance() {
        return new ContractFragment();
    }

    @Override
    public void setClinicals(List<ContractDocItem> contractDocList, int loadType) {
        setLoadDataResult(mContractsAdapter, mSwipeRefreshLayout, contractDocList, loadType);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                ToastUtils.showShort(R.string.add);
                break;
            case R.id.tvNewCon:
                ToastUtils.showShort(R.string.new_contract);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ContractDocItem item = (ContractDocItem) adapter.getItem(position);
        assert item != null;
        ToastUtils.showShort(item.getContName());
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
}
