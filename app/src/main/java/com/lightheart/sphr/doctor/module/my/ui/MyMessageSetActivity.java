package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.module.my.contract.MyMessageSetContract;
import com.lightheart.sphr.doctor.module.my.presenter.MyMessageSetPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-28.
 * Description : 消息设置页面
 */

public class MyMessageSetActivity extends BaseActivity<MyMessageSetPresenter> implements MyMessageSetContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.scNews)
    SwitchCompat scNews;
    @BindView(R.id.scPerson)
    SwitchCompat scPerson;
    private boolean isNewPush;
    private boolean isPersonPush;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_setting;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.message_set, false, 0);

        assert mPresenter != null;
        mPresenter.loadMessageSetData();
    }

    @Override
    public void setDefault(String isArticlePush, String isPersonalPush) {
        isNewPush = TextUtils.equals("Y", isArticlePush);
        isPersonPush = TextUtils.equals("Y", isPersonalPush);
        scNews.setChecked(TextUtils.equals("Y", isArticlePush));
        scPerson.setChecked(TextUtils.equals("Y", isPersonalPush));
    }

    @OnClick({R.id.scNews, R.id.scPerson})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scNews:
                assert mPresenter != null;
                mPresenter.setNewsPush(!isNewPush);
                scNews.setChecked(!isNewPush);
                break;
            case R.id.scPerson:
                assert mPresenter != null;
                mPresenter.setPersonPush(!isPersonPush);
                scPerson.setChecked(!isPersonPush);
                break;
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}