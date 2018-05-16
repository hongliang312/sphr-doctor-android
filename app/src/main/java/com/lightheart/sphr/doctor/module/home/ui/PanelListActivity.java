package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelListAdapter;
import com.lightheart.sphr.doctor.module.home.adapter.PanelSectionAdapter;
import com.lightheart.sphr.doctor.module.home.contract.HomePanelContract;
import com.lightheart.sphr.doctor.module.home.presenter.PanelsPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description : 全部专家组列表
 */

public class PanelListActivity extends BaseActivity<PanelsPresenter> implements HomePanelContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvPanels)
    RecyclerView mRvPanels;
    @Inject
    PanelListAdapter mPanelListAdapter;
    private String flag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_panel;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        flag = getIntent().getStringExtra("flag");
        if (TextUtils.equals("Y", flag)) {
            initToolbar(mToolbar, mTitleTv, mRegiste, R.string.added_panel, false, 0);
        } else {
            initToolbar(mToolbar, mTitleTv, mRegiste, R.string.interesting_panel, false, 0);
        }

        mPanelListAdapter.initData(flag);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new LinearLayoutManager(this));
        mRvPanels.setAdapter(mPanelListAdapter);

        mPanelListAdapter.setOnItemClickListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelList(flag);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PanelsModel item = (PanelsModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(PanelListActivity.this, PanelDetailActivity.class).putExtra("detail", item).putExtra("flag", flag));
    }

    @Override
    public void setPanelData(List<PanelsModel> panelsModels, int loadType, String isMember) {
        setLoadDataResult(mPanelListAdapter, mSwipeRefreshLayout, panelsModels, loadType);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
