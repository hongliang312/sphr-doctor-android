package com.lightheart.sphr.doctor.module.home.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;

import com.lightheart.sphr.doctor.bean.TestingManagement;

import com.lightheart.sphr.doctor.module.home.adapter.TestingManagmentAdapter;
import com.lightheart.sphr.doctor.module.home.contract.TestingManagementContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestingManagementPresenter;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TestingManagementActivity extends BaseActivity<TestingManagementPresenter> implements TestingManagementContract.View, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.listitem)
    RecyclerView listitem;
    @Inject
    TestingManagmentAdapter testingManagmentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_testing;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        initToolbar(mToolbar,mTitleTv,mBtSub,R.string.testingmanagement,false,0);

        listitem.setLayoutManager(new LinearLayoutManager(this));

        View TestingHead = LayoutInflater.from(this).inflate(R.layout.testingmangmenthead, null);
        testingManagmentAdapter.addHeaderView(TestingHead);
        listitem.setAdapter(testingManagmentAdapter);

        testingManagmentAdapter.setOnItemClickListener(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadTestData();

    }

    @Override
    public void setTesting(List<TestingManagement> content, int loadType) {
        testingManagmentAdapter.setNewData(content);
        setLoadDataResult(testingManagmentAdapter,swipeRefreshLayout,content,loadType);

    }


    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        TestingManagement item = (TestingManagement) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(this, TestDetailsActivity.class).putExtra("id",item.getId()));

    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }
}
