package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-29.
 * Description : 系统公告页面
 */

public class HomeNoticeActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notice;
    }

    @Override
    protected void initInjector() {}

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mSub, R.string.system_notice, false, 0);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        tvContent.setText(TextUtils.isEmpty(content) ? "" : content);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
