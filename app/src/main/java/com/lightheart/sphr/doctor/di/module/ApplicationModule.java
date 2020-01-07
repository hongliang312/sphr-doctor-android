package com.lightheart.sphr.doctor.di.module;

import android.content.Context;

import com.lightheart.sphr.doctor.app.DCApplication;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private DCApplication mApplication;

    public ApplicationModule(DCApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
