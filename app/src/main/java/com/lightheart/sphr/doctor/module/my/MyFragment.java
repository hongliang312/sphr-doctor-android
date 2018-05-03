package com.lightheart.sphr.doctor.module.my;

import android.view.View;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.my.contract.MyContract;
import com.lightheart.sphr.doctor.module.my.presenter.MyPresenter;

/**
 * Created by fucp on 2018-4-19.
 * Description :我的页面
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    public static MyFragment newInstance(){
        return new MyFragment();
    }

    @Override
    public void setDocIndo(DoctorBean docIndo) {

    }

}
