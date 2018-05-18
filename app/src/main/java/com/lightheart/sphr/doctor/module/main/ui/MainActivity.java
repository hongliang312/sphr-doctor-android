package com.lightheart.sphr.doctor.module.main.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.module.contracts.ContractFragment;
import com.lightheart.sphr.doctor.module.home.HomeFragment;
import com.lightheart.sphr.doctor.module.my.MyFragment;
import com.lightheart.sphr.doctor.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private int mLastFgIndex;
    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        mNavigation.setOnNavigationItemSelectedListener(this);
        initFragment();
        switchFragment(0);
        RxBus.getInstance().post(new LoginSuccess());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchFragment(0);
                break;
            case R.id.navigation_contract:
                switchFragment(1);
                break;
            case R.id.navigation_my:
                switchFragment(2);
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort(R.string.exit_system);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ContractFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded())
            ft.add(R.id.layout_fragment, targetFg);
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.menuHot) {
            mToolbar.setTitle(R.string.hot_title);
            switchFragment(3);
        } else if (item.getItemId() == R.id.menuSearch) {
            ARouter.getInstance().build("/hotsearch/SearchActivity").navigation();
        }*/
        return super.onOptionsItemSelected(item);
    }

}
