package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.view.CommonTabLayout;
import com.lightheart.sphr.doctor.view.CustomTabEntity;
import com.lightheart.sphr.doctor.view.OnTabSelectListener;
import com.lightheart.sphr.doctor.view.TabEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-14.
 * Description : 患者管理
 */

public class HomePatientManageActivity extends BaseActivity implements OnTabSelectListener, View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
//    @BindView(R.id.tabPatient)
//    CommonTabLayout tabPatientSub;
    @BindView(R.id.vpPatients)
    ViewPager vpPatientSubPage;

//    private final String[] mTitles = {"3个月", "3个月-1年", "1年以上"};
//    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconSelectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_patient_manage;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.patient_manage, false, 0);

//        mTabEntities.clear();
//        for (int i = 0; i < mTitles.length; i++) {
//            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
//        }

        mFragmentList.clear();
        int mPage = 1;
        mFragmentList.add(FragmentOne.newInstance(1, mPage));
//        mFragmentList.add(PatientsFragment.newInstance(2, mPage));
//        mFragmentList.add(PatientsFragment.newInstance(3, mPage));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpPatientSubPage.setAdapter(mAdapter);
//        tabPatientSub.setTabData(mTabEntities);
//        tabPatientSub.setCurrentTab(0);
//        tabPatientSub.setOnTabSelectListener(this);
        vpPatientSubPage.addOnPageChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                ToastUtils.showShort(getString(R.string.patient_search));
                break;
        }
    }

    @Override
    public void onTabSelect(int position) {
        vpPatientSubPage.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
//        tabPatientSub.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
    }


    @Override
    protected boolean showHomeAsUp() {
        return true;
    }


}
