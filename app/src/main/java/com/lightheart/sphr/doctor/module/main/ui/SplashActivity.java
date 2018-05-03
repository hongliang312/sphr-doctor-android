package com.lightheart.sphr.doctor.module.main.ui;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.ConstantsImageUrl;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import com.lightheart.sphr.doctor.utils.RxBus;
import com.lightheart.sphr.doctor.utils.RxSchedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by fucp on 2018-4-10.
 * Description :启动页
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_iv_pic)
    ImageView mIvPic;
    @BindView(R.id.tv_splash_skip)
    TextView tvSplashSkip;
    private int count = 4;
    private Disposable timer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
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
