package com.lightheart.sphr.doctor.module.home.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.view.CommonTabLayout;
import com.lightheart.sphr.doctor.view.CustomTabEntity;
import com.lightheart.sphr.doctor.view.OnTabSelectListener;
import com.lightheart.sphr.doctor.view.TabEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description : 专家组详情页
 */

public class PanelDetailActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mRegiste;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tabPanelSub)
    CommonTabLayout mTabPanelSub;
    @BindView(R.id.vpPanelSubPage)
    ViewPager mVpPanelSubPage;
    private final String[] mTitles = {"成员", "共享内容"};
    private int[] mIconSelectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_panel_detail;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mRegiste, R.string.panel_details, false, 0);

        PanelsModel mPanelsModel = (PanelsModel) getIntent().getSerializableExtra("detail");
        String mFlag = getIntent().getStringExtra("flag");

        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mFragmentList.clear();
        mFragmentList.add(PanelMemberFragment.newIntance(mPanelsModel, mFlag));
        mFragmentList.add(PanelShareFragment.newIntance(mPanelsModel.getDtmAroId()));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mVpPanelSubPage.setAdapter(mAdapter);
        mTabPanelSub.setTabData(mTabEntities);
        mTabPanelSub.setCurrentTab(0);
        mTabPanelSub.setOnTabSelectListener(this);
        mVpPanelSubPage.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        mVpPanelSubPage.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mTabPanelSub.setCurrentTab(position);
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

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
    }

}
