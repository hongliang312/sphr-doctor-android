package com.lightheart.sphr.doctor.di.component;

import android.content.Context;

import com.lightheart.sphr.doctor.di.module.ServiceModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();
}
