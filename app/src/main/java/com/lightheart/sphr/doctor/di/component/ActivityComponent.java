package com.lightheart.sphr.doctor.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.doctor.di.module.ActivityModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerActivity;
import com.lightheart.sphr.doctor.module.home.activity.OnlineConsultantActivity;
import com.lightheart.sphr.doctor.module.home.activity.TestDetailsActivity;
import com.lightheart.sphr.doctor.module.home.activity.TestingManagementActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.NewContractActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.SearchPhoneActivity;
import com.lightheart.sphr.doctor.module.main.ui.LoginActivity;
import com.lightheart.sphr.doctor.module.main.ui.RegisterActivity;
import com.lightheart.sphr.doctor.module.my.ui.FeedBackActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(TestingManagementActivity activity);

    void inject(TestDetailsActivity activity);

    void inject(NewContractActivity activity);

    void inject(SearchPhoneActivity activity);

    void inject(MyHomePageActivity activity);

    void inject(FeedBackActivity activity);

    //void inject(OnlineConsultantActivity activity);

}
