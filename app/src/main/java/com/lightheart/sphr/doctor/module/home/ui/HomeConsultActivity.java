package com.lightheart.sphr.doctor.module.home.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在线咨询和电话咨询Tab页面
 */

public class HomeConsultActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tas)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager viewPager;
    private List<String> datas = new ArrayList<String>();
    private String stringExtra;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_consultant;
    }

    @Override
    protected void initInjector() {
        // mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        stringExtra = getIntent().getStringExtra("tyname");
        if ("电话咨询".equals(stringExtra)){
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.tel_online, false, 0);
        }else {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.consult_online, false, 0);
        }

        datas.add("待处理");
        datas.add("已处理");
        //适配器
        vpsp vpsp = new vpsp(getSupportFragmentManager());
        viewPager.setAdapter(vpsp);
        //进行关联
        tabLayout.setupWithViewPager(viewPager);
    }
    class vpsp extends FragmentPagerAdapter {
        //有参数的构造
        public vpsp(FragmentManager fm) {
            super(fm);
        }
        //返回选项卡的文本 ，，，添加选项卡
        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position);
        }
        //创建fragment对象并返回
        @Override
        public Fragment getItem(int position) {
            HomeConsultSubFragment content = new HomeConsultSubFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", datas.get(position));
            bundle.putString("names",stringExtra);
            content.setArguments(bundle);
            return content;
        }
        //返回数量
        @Override
        public int getCount() {
            return datas.size();
        }
    }
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
