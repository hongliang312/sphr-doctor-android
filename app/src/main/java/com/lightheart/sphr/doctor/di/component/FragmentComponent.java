package com.lightheart.sphr.doctor.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.doctor.di.module.FragmentModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerFragment;
import com.lightheart.sphr.doctor.module.contracts.ContractFragment;
import com.lightheart.sphr.doctor.module.home.ClientcenteredCounseling;
import com.lightheart.sphr.doctor.module.home.HomeFragment;
import com.lightheart.sphr.doctor.module.home.ui.PanelMemberFragment;
import com.lightheart.sphr.doctor.module.home.ui.PanelShareFragment;
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

    void inject(PanelShareFragment fragment);

    void inject(ClientcenteredCounseling fragment);

    void inject(PanelMemberFragment fragment);


}
