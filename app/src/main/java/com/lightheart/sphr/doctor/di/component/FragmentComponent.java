package com.lightheart.sphr.doctor.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.doctor.di.module.FragmentModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerFragment;
import com.lightheart.sphr.doctor.module.contracts.ContractFragment;
import com.lightheart.sphr.doctor.module.home.HomeFragment;
import com.lightheart.sphr.doctor.module.my.MyFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeFragment fragment);

    void inject(ContractFragment fragment);

    void inject(MyFragment fragment);



}
