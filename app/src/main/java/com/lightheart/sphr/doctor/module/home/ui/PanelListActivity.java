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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.HomePanelModel;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.bean.ShareClinical2PanelParam;
import com.lightheart.sphr.doctor.module.home.adapter.PanelListAdapter;
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
    private String mTodo;
    private int linkId;
    private String shareTitle;
    private String sharerName;

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
        mTodo = getIntent().getStringExtra("TODO");// 分享临床试验到专家组
        linkId = getIntent().getIntExtra("linkId", 0);
        shareTitle = getIntent().getStringExtra("shareTitle");
        sharerName = getIntent().getStringExtra("sharerName");

        int title = TextUtils.equals("Y", flag) ? R.string.added_panel : R.string.interesting_panel;
        initToolbar(mToolbar, mTitleTv, mRegiste, title, false, 0);

        mPanelListAdapter.initData(flag);

        // 设置RecyclerView
        mRvPanels.setLayoutManager(new LinearLayoutManager(this));
        mRvPanels.setAdapter(mPanelListAdapter);

        mSwipeRefreshLayout.setEnabled(false);
        mPanelListAdapter.setOnItemClickListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelAllList(flag);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PanelsModel item = (PanelsModel) adapter.getItem(position);
        assert item != null;
        if (TextUtils.equals("SHARE", mTodo)) {
            ShareClinical2PanelParam param = new ShareClinical2PanelParam();
            param.dtmAroId = item.getDtmAroId();
            param.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
            param.linkId = linkId;
            param.sharerName = sharerName;
            param.shareTitle = shareTitle;
            assert mPresenter != null;
            mPresenter.share2Panel(param);
        } else {
            startActivity(new Intent(PanelListActivity.this, PanelDetailActivity.class).putExtra("detail", item).putExtra("flag", flag));
        }
    }

    // 不用
    @Override
    public void setPanelData(HomePanelModel panelsModels, int loadType) {
    }

    @Override
    public void setPanelList(List<PanelsModel> panelsModels, int loadType) {
        if (panelsModels != null && panelsModels.size() > 0)
            setLoadDataResult(mPanelListAdapter, mSwipeRefreshLayout, panelsModels, loadType);
        else
            mPanelListAdapter.setEmptyView(R.layout.layout_empty, (ViewGroup) mRvPanels.getParent());
    }

    @Override
    public void successShare() {
        ToastUtils.showShort("分享成功");
        this.finish();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
