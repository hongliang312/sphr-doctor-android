package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.FeedBackBean;
import com.lightheart.sphr.doctor.module.my.contract.FeedBackContract;
import com.lightheart.sphr.doctor.module.my.presenter.FeedBackPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-12.
 * Description :意见反馈页面
 */

public class FeedBackActivity extends BaseActivity<FeedBackPresenter> implements FeedBackContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.etFeedBack)
    EditText etFeedBack;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.feedback, false, 0);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick(R.id.tvSubmit)
    public void onClick(View view) {
        FeedBackBean feedBackBean = new FeedBackBean();
        feedBackBean.setUid(SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY));
        feedBackBean.setContent(etFeedBack.getText().toString().trim());
        feedBackBean.setAppVersion(AppUtils.getAppVersionName());
        feedBackBean.setMobileModel(DeviceUtils.getModel());
        feedBackBean.setMobileSystem(DeviceUtils.getSDKVersion() + "");
        if (TextUtils.isEmpty(feedBackBean.getContent())) {
            ToastUtils.showShort(getString(R.string.feed_back_hint1));
            return;
        }
        assert mPresenter != null;
        mPresenter.submitFeedBack(feedBackBean);
    }

    @Override
    public void successSub() {
        ToastUtils.showShort("您已反馈成功！");
        this.finish();
    }
}
