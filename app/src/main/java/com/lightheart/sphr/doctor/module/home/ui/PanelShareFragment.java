package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.PanelShareModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelShareAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PanelShareContract;
import com.lightheart.sphr.doctor.module.home.presenter.PanelSharePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description :专家组共享内容页面
 */

public class PanelShareFragment extends BaseFragment<PanelSharePresenter> implements PanelShareContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvMember)
    RecyclerView rvMember;
    @Inject
    PanelShareAdapter mPanelShareAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panel_member_share;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        int id = getArguments().getInt("id");

        // 设置RecyclerView
        rvMember.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMember.setAdapter(mPanelShareAdapter);
        swipeRefreshLayout.setEnabled(false);
        mPanelShareAdapter.setOnItemClickListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelShare(id);
    }

    public static PanelShareFragment newIntance(int dtmAroId) {
        PanelShareFragment fragment = new PanelShareFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", dtmAroId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPanelShare(List<PanelShareModel> panelsModels, int loadType) {
        setLoadDataResult(mPanelShareAdapter, swipeRefreshLayout, panelsModels, loadType);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PanelShareModel item = (PanelShareModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(getActivity(), ClinicalRecruitDetailActivity.class).putExtra("id", item.getLinkId()));
    }

    // 暂时不需要
    @Override
    public void success2ApplyPanel() {
    }

}
