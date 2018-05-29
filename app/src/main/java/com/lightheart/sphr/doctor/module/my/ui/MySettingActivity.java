package com.lightheart.sphr.doctor.module.my.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.EventModel;
import com.lightheart.sphr.doctor.module.main.ui.LoginActivity;
import com.lightheart.sphr.doctor.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.llVersion)
    LinearLayout llVersion;
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
        RxBus.getInstance().toFlowable(EventModel.class).subscribe(new Consumer<EventModel>() {
            @Override
            public void accept(EventModel event) throws Exception {
                if (event.isModify) finish();
            }
        });

        tvCurrentVersion.setText(TextUtils.isEmpty(AppUtils.getAppVersionName()) ? "" : AppUtils.getAppVersionName());

    }

    @OnClick({R.id.tvModifyPsd, R.id.tvMessageSet, R.id.llVersion, R.id.tvLogOut,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPsd:
                startActivity(new Intent(MySettingActivity.this, ModifyPasswordActivity.class));
                break;
            case R.id.tvMessageSet:
                startActivity(new Intent(MySettingActivity.this, MyMessageSetActivity.class));
                break;
            case R.id.llVersion:
                ToastUtils.showShort(tvCurrentVersion.getText().toString().trim());
                break;
            case R.id.tvLogOut:
                // 设置退出登陆
                SPUtils.getInstance(Constant.SHARED_NAME).clear();
                EventModel event = new EventModel();
                event.isLogout = true;
                startActivity(new Intent(MySettingActivity.this, LoginActivity.class));
                RxBus.getInstance().post(event);
                finish();
                break;
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
