package com.lightheart.sphr.doctor.di.component;

import android.app.Activity;
import android.content.Context;

import com.lightheart.sphr.doctor.di.module.ActivityModule;
import com.lightheart.sphr.doctor.di.scope.ContextLife;
import com.lightheart.sphr.doctor.di.scope.PerActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.NewContractActivity;
import com.lightheart.sphr.doctor.module.contracts.ui.SearchPhoneActivity;
import com.lightheart.sphr.doctor.module.home.ui.ClinicalRecruitDetailActivity;
import com.lightheart.sphr.doctor.module.home.ui.ClinicalTrailManageActivity;
import com.lightheart.sphr.doctor.module.home.ui.ClinicalTrailManageDetailActivity;
import com.lightheart.sphr.doctor.module.home.ui.CreatePanelActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomeClinicalRecruitActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomeConsultSubDetailActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomePanelActivity;
import com.lightheart.sphr.doctor.module.home.ui.PanelListActivity;
import com.lightheart.sphr.doctor.module.home.ui.PanelMessageListActivity;
import com.lightheart.sphr.doctor.module.home.ui.PatientRecordsActivity;
import com.lightheart.sphr.doctor.module.home.ui.SelectContactActivity;
import com.lightheart.sphr.doctor.module.main.ui.LoginActivity;
import com.lightheart.sphr.doctor.module.main.ui.RegisterActivity;
import com.lightheart.sphr.doctor.module.my.ui.AreaActivity;
import com.lightheart.sphr.doctor.module.my.ui.AuthenticationActivity;
import com.lightheart.sphr.doctor.module.my.ui.DepartmentActivity;
import com.lightheart.sphr.doctor.module.my.ui.FeedBackActivity;
import com.lightheart.sphr.doctor.module.my.ui.ModifyPasswordActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyPersonalInfoActivity;

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

    void inject(ClinicalTrailManageActivity activity);

    void inject(ClinicalTrailManageDetailActivity activity);

    void inject(NewContractActivity activity);

    void inject(SearchPhoneActivity activity);

    void inject(MyHomePageActivity activity);

    void inject(FeedBackActivity activity);

    void inject(HomePanelActivity activity);

    void inject(PanelListActivity activity);

    void inject(CreatePanelActivity activity);

    void inject(SelectContactActivity activity);

    void inject(HomeClinicalRecruitActivity activity);

    void inject(ClinicalRecruitDetailActivity activity);

    void inject(HomeConsultSubDetailActivity activity);

    void inject(PatientRecordsActivity activity);

    void inject(MyPersonalInfoActivity activity);

    void inject(AreaActivity activity);

    void inject(DepartmentActivity activity);

    void inject(AuthenticationActivity activity);

    void inject(PanelMessageListActivity activity);

    void inject(ModifyPasswordActivity activity);

}
