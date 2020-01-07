package com.lightheart.sphr.doctor.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.guwu.common.utils.ToastUtil;
import com.lightheart.sphr.doctor.di.component.ApplicationComponent;
import com.lightheart.sphr.doctor.di.component.DaggerApplicationComponent;
import com.lightheart.sphr.doctor.di.module.ApplicationModule;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

/**
 * Created by fucp on 2018-4-10.
 * Description :
 */

public class DCApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static DCApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initApplicationComponent();
        Utils.init(this);

        StyleManager s = new StyleManager();
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);
        LoadingDialog.initStyle(s);

        ToastUtil.init(this);

    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static DCApplication getInstance() {
        return mInstance;
    }

}
