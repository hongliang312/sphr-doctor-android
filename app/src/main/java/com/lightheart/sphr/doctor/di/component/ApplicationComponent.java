package com.lightheart.sphr.doctor.di.component;

import android.content.Context;

import com.lightheart.sphr.doctor.di.module.ApplicationModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerApp;

import dagger.Component;


@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}