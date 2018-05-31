package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.ConsultModel;
import com.lightheart.sphr.doctor.module.home.adapter.ConsultListAdapter;
import com.lightheart.sphr.doctor.module.home.contract.ConsultingListContract;
import com.lightheart.sphr.doctor.module.home.presenter.ConsultListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 在线咨询和电话咨询列表页面
 */

public class HomeConsultSubFragment extends BaseFragment<ConsultListPresenter> implements ConsultingListContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvConsult)
    RecyclerView rvConsult;
    @Inject
    ConsultListAdapter mAdapter;
    private String consultType;
    private String consultStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_consult_sub;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        assert getArguments() != null;
        consultType = getArguments().getString("consult_type");
        consultStatus = getArguments().getString("consult_status");
        assert mPresenter != null;
        if (TextUtils.equals("TEL", consultType)) {
            mPresenter.loadTelConsultData(consultStatus);
        } else if (TextUtils.equals("ONLINE", consultType)) {
            mPresenter.loadOnlineData(consultStatus);
        }

        rvConsult.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvConsult.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

    }

    public static HomeConsultSubFragment newInstance(String consultType, String ser_cst_s_ing) {
        HomeConsultSubFragment fragment = new HomeConsultSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString("consult_type", consultType);
        bundle.putString("consult_status", ser_cst_s_ing);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setOnlineData(List<ConsultModel> content) {
        if (content != null && content.size() > 0)
            mAdapter.setNewData(content);
        else mAdapter.setEmptyView(R.layout.layout_empty, (ViewGroup) rvConsult.getParent());
    }

    @Override
    public void setTelConsultData(List<ConsultModel> content) {
        if (content != null && content.size() > 0)
            mAdapter.setNewData(content);
        else mAdapter.setEmptyView(R.layout.layout_empty, (ViewGroup) rvConsult.getParent());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ConsultModel item = (ConsultModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(getActivity(), HomeConsultSubDetailActivity.class)
                .putExtra("consult_type", consultType)
                .putExtra("consult_status", consultStatus)
                .putExtra("tvPatientName", item.getName())
                .putExtra("id", item.getConsultId()));
    }
}
