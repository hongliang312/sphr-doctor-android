package com.lightheart.sphr.doctor.module.main.ui;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.lightheart.sphr.doctor.app.Constant.RC_READ_AND_WRITE_AND_CAMERA;

/**
 * Created by fucp on 2018-4-10.
 * Description :启动页
 */

public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.splash_iv_pic)
    ImageView mIvPic;
    @BindView(R.id.tv_splash_skip)
    TextView tvSplashSkip;
    private int count = 3;
    private Disposable timer;
    private static final String[] READ_AND_WRITE_AND_CAMERA =
            {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        // 第一次进入App时权限检查以及申请权限
        boolean isFirstLogin = SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.IS_FIRST_LOGIN_KEY, false);
        if (!isFirstLogin) {
            // 检查权限
            checkNeedPermissions();
        } else {
            timerCountDown();
        }
    }

    @AfterPermissionGranted(RC_READ_AND_WRITE_AND_CAMERA)
    public void checkNeedPermissions() {
        if (hasCameraAndStoragePermissions()) {
            // 已获取权限开始倒计时
            timerCountDown();
        } else {
            // 请求权限
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_camera),
                    RC_READ_AND_WRITE_AND_CAMERA,
                    READ_AND_WRITE_AND_CAMERA);
        }
    }

    private boolean hasCameraAndStoragePermissions() {
        return EasyPermissions.hasPermissions(this, READ_AND_WRITE_AND_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @android.support.annotation.NonNull String[] permissions,
                                           @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @android.support.annotation.NonNull List<String> perms) {
        // 不论获得权限还是被拒绝，一律进入倒计时，并进入主页面或者登陆页面
        timerCountDown();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @android.support.annotation.NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
        // 不论获得权限还是被拒绝，一律进入倒计时，并进入主页面或者登陆页面
        timerCountDown();
    }

    private void timerCountDown() {
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.IS_FIRST_LOGIN_KEY, true);
        timer = Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                }).compose(RxSchedulers.<Long>ioMain())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        tvSplashSkip.setText("跳过(" + aLong + "s)");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        toMainActivity();
                    }
                });
    }


    /**
     * 跳转到主页面
     */
    private void toMainActivity() {
        if (SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.LOGIN_KEY, false))
            startActivity(new Intent(this, MainActivity.class));
        else startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        this.finish();
    }

    @OnClick(R.id.tv_splash_skip)
    public void onClick(View view) {
        toMainActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && !timer.isDisposed()) {
            timer.dispose();
        }
    }

}
