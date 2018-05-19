package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-19.
 * Description : 我的设置页面
 */

public class MySettingActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.my_page, false, 0);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
