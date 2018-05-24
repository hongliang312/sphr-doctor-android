package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.tvModifyPsd)
    TextView tvModifyPsd;
    @BindView(R.id.tvMessageSet)
    TextView tvMessageSet;
    @BindView(R.id.tvCurrentVersion)
    TextView tvCurrentVersion;
    @BindView(R.id.tvLogOut)
    TextView tvLogOut;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.setting, false, 0);
    }

    @OnClick({R.id.tvModifyPsd, R.id.tvMessageSet, R.id.tvCurrentVersion, R.id.tvLogOut,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPsd:

                break;
            case R.id.tvMessageSet:

                break;
            case R.id.tvCurrentVersion:

                break;
            case R.id.tvLogOut:
                // 设置退出登陆
                SPUtils.getInstance(Constant.SHARED_NAME).clear();
                RxBus.getInstance().post(new LoginSuccess());
                finish();
                break;
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
