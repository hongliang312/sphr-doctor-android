package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

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
 * 在线咨询和电话咨询Tab页面
 */

public class HomeConsultActivity extends BaseActivity implements ViewPager.OnPageChangeListener, OnTabSelectListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tabConsultSub)
    CommonTabLayout tabConsultSub;
    @BindView(R.id.vpConsultSubPage)
    ViewPager vpConsultSubPage;
    private final String[] mTitles = {"待处理", "已处理"};
    private int[] mIconSelectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_consultant;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        String consultType = getIntent().getStringExtra("consult_type");
        if (TextUtils.equals("TEL", consultType)) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.tel_online, false, 0);
        } else if (TextUtils.equals("ONLINE", consultType)) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.consult_online, false, 0);
        }

        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mFragmentList.clear();
        mFragmentList.add(HomeConsultSubFragment.newInstance(consultType, "SER_CST_S_ING"));
        mFragmentList.add(HomeConsultSubFragment.newInstance(consultType, "SER_CST_S_END"));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpConsultSubPage.setAdapter(mAdapter);
        tabConsultSub.setTabData(mTabEntities);
        tabConsultSub.setCurrentTab(0);
        tabConsultSub.setOnTabSelectListener(this);
        vpConsultSubPage.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        vpConsultSubPage.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        tabConsultSub.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

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
