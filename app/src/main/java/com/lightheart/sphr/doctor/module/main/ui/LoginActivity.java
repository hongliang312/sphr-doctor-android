package com.lightheart.sphr.doctor.module.main.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.EventModel;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.module.main.contract.LoginContract;
import com.lightheart.sphr.doctor.module.main.presenter.LoginPresenter;
import com.lightheart.sphr.doctor.utils.CheckContentUtil;
import com.lightheart.sphr.doctor.utils.RxBus;
import com.lightheart.sphr.doctor.view.ProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by fucp on 2018-4-11.
 * Description :登录页面
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.et_mobile)
    TextInputEditText mEtMobile;
    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.tv_forget_psd)
    TextView mTvForgetPsd;
    @BindView(R.id.tv_verification_code_login)
    TextView mTvVerificationCodeLogin;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.login, true, R.string.register);
        mEtMobile.setText(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MOBILE_KEY));
        mEtPassword.setText(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.PASSWORD_KEY));
        mTvForgetPsd.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        // 登陆成功重新设置用户新
        RxBus.getInstance().toFlowable(EventModel.class).subscribe(new Consumer<EventModel>() {
            @Override
            public void accept(EventModel event) throws Exception {
                if (event.isLogin) finish();
            }
        });
    }

    @Override
    public void loginSuccess(DoctorBean user) {
        ProgressBar.dis();
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LOGIN_KEY, true);
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MOBILE_KEY, user.getMobile());
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.PASSWORD_KEY, mEtPassword.getText().toString());
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.USER_KEY, user.getId());
        // 登陆成功通知其他界面刷新
        EventModel event = new EventModel();
        event.isLogin = true;
        RxBus.getInstance().post(event);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
    }

    @OnClick({R.id.tv_forget_psd, R.id.tv_verification_code_login, R.id.btnLogin, R.id.bt_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_psd:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("mFlag", "MODIFYPSD"));
                break;
            case R.id.tv_verification_code_login:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("mFlag", "VERCODELOGIN"));
                break;
            case R.id.btnLogin:
                LoginRequest params = new LoginRequest();
                LoginRequest.Data data = new LoginRequest.Data();
                String mobile = mEtMobile.getText().toString();
                String password = mEtPassword.getText().toString();
                if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
                    ToastUtils.showShort(R.string.mobile_or_password_can_not_be_empty);
                    return;
                }
                if (!CheckContentUtil.checkPhone(mobile)) {
                    ToastUtils.showShort(R.string.mobile_error);
                    return;
                }
                data.mobile = mobile;
                data.password = password;
                params.data = new Gson().toJson(data);
                assert mPresenter != null;
                ProgressBar.show(getSupportFragmentManager());
                mPresenter.login(params);
                break;
            case R.id.bt_sub:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("mFlag", "REGISTER"));
                break;
        }
    }


}
