package com.lightheart.sphr.doctor.module.my.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.lightheart.sphr.doctor.bean.AreaModel;
import com.lightheart.sphr.doctor.module.my.adapter.AreaAdapter;
import com.lightheart.sphr.doctor.module.my.contract.AreaContract;
import com.lightheart.sphr.doctor.module.my.presenter.AreaPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-22.
 * Description : 地区一级页面
 */

public class AreaActivity extends BaseActivity<AreaPresenter> implements AreaContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.rvArea)
    RecyclerView rvArea;
    @Inject
    AreaAdapter mAdapter;
    private int areaLevel = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.distract_select, false, 0);

        rvArea.setLayoutManager(new LinearLayoutManager(this));
        rvArea.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        assert mPresenter != null;
        mPresenter.loadAreaData();
    }

    @Override
    public void setAreas(List<AreaModel> titles) {
        if (titles != null && titles.size() > 0)
            mAdapter.setNewData(titles);
        else
            initEmptyView(mAdapter, rvArea);
    }

    @Override
    public void setChildAreas(List<AreaModel> childAreas) {
        areaLevel += 1;
        if (childAreas != null && childAreas.size() > 0)
            mAdapter.setNewData(childAreas);
        else
            initEmptyView(mAdapter, rvArea);
    }

    @Override
    public void setDepartment(List<AreaModel> departments) {
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AreaModel item = (AreaModel) adapter.getItem(position);
        assert item != null;
        assert mPresenter != null;
        if (areaLevel < 3) {
            mPresenter.loadChildAreaData(item.getId());
        } else {
            this.setResult(Activity.RESULT_OK, new Intent().putExtra("distractId", item.getId()).putExtra("distractName", item.getFullName()));
            this.finish();
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
