package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.PanelMessageModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelMessageAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PanelMessageListContract;
import com.lightheart.sphr.doctor.module.home.presenter.PanelMessageListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-25.
 * Description : 专家组消息列表页
 */

public class PanelMessageListActivity extends BaseActivity<PanelMessageListPresenter> implements PanelMessageListContract.View, PanelMessageAdapter.SlideItemListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvNewContracts)
    RecyclerView mRvPanelMessage;
    @Inject
    PanelMessageAdapter messageAdapter;

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
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.mes, false, 0);

        messageAdapter.init(this);
        //  设置RecyclerView
        mRvPanelMessage.setLayoutManager(new LinearLayoutManager(this));
        mRvPanelMessage.setAdapter(messageAdapter);

        messageAdapter.setOnSlideItemListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadPanelMessage();
    }

    @Override
    public void setPanelMessage(List<PanelMessageModel> panelMessageModels, int loadType) {
        setLoadDataResult(messageAdapter, mSwipeRefreshLayout, panelMessageModels, loadType);
        if (panelMessageModels != null && panelMessageModels.size() == 0)
            initEmptyView(messageAdapter, mRvPanelMessage);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void itemClick(View view, int position, PanelMessageModel item) {
    }

    @Override
    public void accept(View view, int position, PanelMessageModel item) {
        if (item != null) {
            item.setDuid(SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY));
            item.setApplyStatus("ADD");
            assert mPresenter != null;
            mPresenter.updateApplyDtm(item);
        }
    }

    @Override
    public void deleteClick(View view, int position, PanelMessageModel item) {
        if (item != null) {
            item.setDuid(SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY));
            item.setApplyStatus("REF");
            assert mPresenter != null;
            mPresenter.updateApplyDtm(item);
        }
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.loadPanelMessage();
    }
}
