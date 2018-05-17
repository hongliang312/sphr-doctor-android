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

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PanelSection;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelSectionAdapter;
import com.lightheart.sphr.doctor.module.home.contract.HomePanelContract;
import com.lightheart.sphr.doctor.module.home.presenter.PanelsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-14.
 * Description : 专家组页面
 */

public class HomePanelActivity extends BaseActivity<PanelsPresenter> implements HomePanelContract.View, View.OnClickListener, PanelSectionAdapter.SectionItemListener {

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
    private List<PanelSection> panelSectionList = new ArrayList<>();
    private PanelSectionAdapter mPanelSectionAdapter;

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
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.panel_manage, true, R.string.mes);

        mPanelSectionAdapter = new PanelSectionAdapter(R.layout.item_panel, R.layout.section_panel, panelSectionList);

        // 设置footer
        View footerView = getLayoutInflater().inflate(R.layout.footer_panel, (ViewGroup) mRvPanels.getParent(), false);
        TextView tvCreate = footerView.findViewById(R.id.tvCreate);
        tvCreate.setText(getString(R.string.create_panel));
        tvCreate.setOnClickListener(this);
        mPanelSectionAdapter.addFooterView(footerView);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new LinearLayoutManager(this));
        mRvPanels.setAdapter(mPanelSectionAdapter);

        mPanelSectionAdapter.setOnSectionItemListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelList("N");
        mPresenter.loadPanelList("Y");
    }

    @Override
    public void setPanelData(List<PanelsModel> panelsModels, int loadType, String isMember) {
        if (TextUtils.equals("Y", isMember)) {
            panelSectionList.add(new PanelSection(true, getString(R.string.added_panel), false));
            List<PanelsModel> subList = panelsModels.subList(0, 3);
            for (PanelsModel item : subList) {
                item.setAdded(true);
                panelSectionList.add(new PanelSection(item));
            }
        } else if (TextUtils.equals("N", isMember)) {
            panelSectionList.add(new PanelSection(true, getString(R.string.interesting_panel), true));
            List<PanelsModel> subList = panelsModels.subList(0, 3);
            for (PanelsModel item : subList) {
                item.setAdded(false);
                panelSectionList.add(new PanelSection(item));
            }
        }
        setLoadDataResult(mPanelSectionAdapter, mSwipeRefreshLayout, panelSectionList, loadType);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View view) {
        // 创建专家组
        startActivity(new Intent(HomePanelActivity.this, CreatePanelActivity.class));
    }

    @Override
    public void tvAllClick(View view, int position, PanelSection item) {
        assert item != null;
        startActivity(new Intent(HomePanelActivity.this, PanelListActivity.class).putExtra("flag", item.isMore() ? "N" : "Y"));
    }

    @Override
    public void itemClick(View view, int position, PanelSection item) {
        assert item != null;
        startActivity(new Intent(HomePanelActivity.this, PanelDetailActivity.class).putExtra("detail", item.t).putExtra("flag", item.t.isAdded() ? "Y" : "N"));

    }
}
