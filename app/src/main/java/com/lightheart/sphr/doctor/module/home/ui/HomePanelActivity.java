package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.HomePanelModel;
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
    Button mMessage;
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
        initToolbar(mToolbar, mTitleTv, mMessage, R.string.panel_manage, true, R.string.mes);

        mPanelSectionAdapter = new PanelSectionAdapter(R.layout.item_panel, R.layout.section_panel, panelSectionList);

        // 设置footer
        View footerView = getLayoutInflater().inflate(R.layout.footer_panel, (ViewGroup) mRvPanels.getParent(), false);
        TextView tvCreate = footerView.findViewById(R.id.tvCreate);
        tvCreate.setText(getString(R.string.create_panel));
        tvCreate.setOnClickListener(this);
        mPanelSectionAdapter.addFooterView(footerView);
        mMessage.setOnClickListener(this);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new LinearLayoutManager(this));
        mRvPanels.setAdapter(mPanelSectionAdapter);

        mSwipeRefreshLayout.setEnabled(false);
        mPanelSectionAdapter.setOnSectionItemListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelList();
    }

    @Override
    public void setPanelData(HomePanelModel panelsModels, int loadType) {
        List<PanelsModel> ownerGroupList = new ArrayList<>();
        panelSectionList.add(new PanelSection(true, getString(R.string.added_panel), false));
        if (panelsModels.owerGroupList.size() > 3) {
            ownerGroupList = panelsModels.owerGroupList.subList(0, 3);
        } else {
            ownerGroupList.addAll(panelsModels.owerGroupList);
        }
        for (PanelsModel item : ownerGroupList) {
            item.setAdded(true);
            panelSectionList.add(new PanelSection(item));
        }
        panelSectionList.add(new PanelSection(true, getString(R.string.interesting_panel), true));
        List<PanelsModel> otherGroupList = panelsModels.otherGroupList.subList(0, 3);
        for (PanelsModel item : otherGroupList) {
            item.setAdded(false);
            panelSectionList.add(new PanelSection(item));
        }
        setLoadDataResult(mPanelSectionAdapter, mSwipeRefreshLayout, panelSectionList, loadType);
    }

    // 暂时不用
    @Override
    public void setPanelList(List<PanelsModel> panelsModels, int loadType) {
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                startActivity(new Intent(HomePanelActivity.this, PanelMessageListActivity.class));
                break;
            case R.id.tvCreate:
                // 创建专家组
                startActivity(new Intent(HomePanelActivity.this, CreatePanelActivity.class));
                break;
        }
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

    // 暂时不用
    @Override
    public void successShare() {
    }


}
