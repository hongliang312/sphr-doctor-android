package com.lightheart.sphr.doctor.module.main.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
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
import com.lightheart.sphr.doctor.module.main.contract.RegisterContract;
import com.lightheart.sphr.doctor.module.main.presenter.RegisterPresenter;
import com.lightheart.sphr.doctor.utils.CheckContentUtil;
import com.lightheart.sphr.doctor.utils.RxBus;
import com.lightheart.sphr.doctor.utils.RxSchedulers;
import com.lightheart.sphr.doctor.view.ProgressBar;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by fucp on 2018-4-11.
 * Description :注册页面
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.etMobile)
    TextInputEditText etMobile;
    @BindView(R.id.etAuthCode)
    TextInputEditText etAuthCode;
    @BindView(R.id.tvAuthCode)
    TextView tvAuthCode;
    @BindView(R.id.tilPassword)
    TextInputLayout mTilPassword;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.llTermUse)
    LinearLayout mLlTermUse;
    @BindView(R.id.tvTermUse)
    TextView mTvTermUse;
    @BindString(R.string.app_name)
    String tag;
    private int count = 60;
    private Disposable timer;
    private String mFlag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mFlag = getIntent().getStringExtra("mFlag");
        if (TextUtils.equals(mFlag, "REGISTER")) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.register, false, 0);
            tvRegister.setText(R.string.register);
            mLlTermUse.setVisibility(View.VISIBLE);
            mTilPassword.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(mFlag, "MODIFYPSD")) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.forgetPsd, false, 0);
            tvRegister.setText(R.string.modifyPsd);
            mLlTermUse.setVisibility(View.GONE);
            mTilPassword.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(mFlag, "VERCODELOGIN")) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.verificationCodeLogin, false, 0);
            tvRegister.setText(R.string.verificationCodeLogin);
            mLlTermUse.setVisibility(View.GONE);
            mTilPassword.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tvAuthCode, R.id.tvRegister})
    public void onClick(View view) {
        String mobile = etMobile.getText().toString();
        String authCode = etAuthCode.getText().toString();
        String password = etPassword.getText().toString();
        switch (view.getId()) {
            case R.id.tvAuthCode:
                LoginRequest params = new LoginRequest();
                LoginRequest.Data data = new LoginRequest.Data();
                if (check("AUTHCODE", mobile, authCode, password)) {
                    data.mobile = mobile;
                    data.smsType = TextUtils.equals(mFlag, "REGISTER") ? 1 : (TextUtils.equals(mFlag, "MODIFYPSD") ? 2 : 3);
                    params.data = new Gson().toJson(data);
                    LogUtils.e(tag, new Gson().toJson(params));
                    assert mPresenter != null;
                    ProgressBar.show(getSupportFragmentManager());
                    mPresenter.sendAuthCode(params);
                }
                break;
            case R.id.tvRegister:
                LoginRequest verCodeParma = new LoginRequest();
                LoginRequest.Data authCodeData = new LoginRequest.Data();
                if (TextUtils.equals(mFlag, "VERCODELOGIN")) {
                    if (check("VERCODELOGIN", mobile, authCode, password)) {
                        authCodeData.code = authCode;
                        authCodeData.mobile = mobile;
                        verCodeParma.data = new Gson().toJson(authCodeData);
                        LogUtils.e(tag, new Gson().toJson(verCodeParma));
                        assert mPresenter != null;
                        ProgressBar.show(getSupportFragmentManager());
                        mPresenter.verCodeLogin(verCodeParma);
                    }
                } else  {
                    if (check("REGISTER", mobile, authCode, password)) {
                        authCodeData.code = authCode;
                        authCodeData.mobile = mobile;
                        LogUtils.e(tag, new Gson().toJson(authCodeData));
                        assert mPresenter != null;
                        ProgressBar.show(getSupportFragmentManager());
                        mPresenter.verifyAuthCode(authCodeData, password, mFlag);
                    }
                }
                break;
            case R.id.tvTermUse:
                ToastUtils.showShort(R.string.terms_use);
                break;
        }
    }

    private void transform() {
        tvAuthCode.setEnabled(false);
        timer = Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take(count)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                }).compose(RxSchedulers.<Long>ioMain())
                .subscribe(new Consumer<Long>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        tvAuthCode.setText(aLong + getString(R.string.authCodeHint));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        tvAuthCode.setEnabled(true);
                        tvAuthCode.setText(R.string.send_auth_code);
                    }
                });
    }

    private boolean check(String flag, String mobile, String authCode, String password) {
        if (StringUtils.isEmpty(mobile)) {
            ToastUtils.showShort(R.string.mobile_can_not_be_empty);
            return false;
        }
        if (!CheckContentUtil.checkPhone(mobile)) {
            ToastUtils.showShort(R.string.mobile_error);
            return false;
        }
        if (TextUtils.equals(flag, "REGISTER")) {
            if (StringUtils.isEmpty(authCode)) {
                ToastUtils.showShort(R.string.authcode_can_not_be_empty);
                return false;
            }
            if (StringUtils.isEmpty(password)) {
                ToastUtils.showShort(R.string.password_can_not_be_empty);
                return false;
            }
        } else if (TextUtils.equals(flag, "VERCODELOGIN")) {
            if (StringUtils.isEmpty(authCode)) {
                ToastUtils.showShort(R.string.authcode_can_not_be_empty);
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void sendCodeSucess() {
        transform();
        ProgressBar.dis();
        ToastUtils.showShort(R.string.authCodeSended);
    }

    @Override
    public void registerSuccess(DoctorBean user) {
        ProgressBar.dis();
        new AlertDialog.Builder(this)
                .setTitle(R.string.appName)
                .setMessage("请前往登录页面重新登录！")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void verCodeSuccess(DoctorBean user) {
        ProgressBar.dis();
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LOGIN_KEY, true);
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MOBILE_KEY, user.getMobile());
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.USER_KEY, user.getId());
        // 登陆成功通知其他界面刷新
        EventModel event = new EventModel();
        event.isLogin = true;
        RxBus.getInstance().post(event);
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }

    @Override
    public void modifySuccess() {
        ProgressBar.dis();
        new AlertDialog.Builder(this)
                .setTitle(R.string.appName)
                .setMessage("请前往登录页面重新登录！")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && !timer.isDisposed()) {
            timer.dispose();
        }
    }


}
