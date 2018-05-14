package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-12.
 * Description :认证界面
 */

public class AuthenticationActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.ivSelect)
    ImageView ivSelect;
    @BindView(R.id.tvUpload)
    TextView tvUpload;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.authentication, false, 0);
    }

    @OnClick({R.id.ivSelect, R.id.tvUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSelect:
                ToastUtils.showShort("选择图片");
                ImageLoaderUtils.display(this, ivSelect, R.mipmap.banner_1);
                break;
            case R.id.tvUpload:
                ToastUtils.showShort("上传");
                break;
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
