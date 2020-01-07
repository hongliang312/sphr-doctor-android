package com.lightheart.sphr.doctor.module.my.ui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.EventModel;
import com.lightheart.sphr.doctor.bean.ModifyPsdParam;
import com.lightheart.sphr.doctor.module.main.ui.LoginActivity;
import com.lightheart.sphr.doctor.module.my.contract.ModifyPasswordContract;
import com.lightheart.sphr.doctor.module.my.presenter.ModifyPasswordPresenter;
import com.lightheart.sphr.doctor.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fucp on 2018-5-25.
 * Description : 修改密码页面
 */

public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> implements ModifyPasswordContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.etOldPsd)
    TextInputEditText etOldPsd;
    @BindView(R.id.etNewPsd)
    TextInputEditText etNewPsd;
    @BindView(R.id.etSubNewPsd)
    TextInputEditText etSubNewPsd;
    @BindView(R.id.tvModifyPsd)
    TextView tvModifyPsd;
    private ModifyPsdParam.Data data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_psd;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mSub, R.string.modifyPsd, false, 0);
    }

    @OnClick(R.id.tvModifyPsd)
    public void onClick(View view) {
        check2Modify();
    }

    private void check2Modify() {
        String oldPsd = etOldPsd.getText().toString().trim();
        String newPsd = etNewPsd.getText().toString().trim();
        String subNewPsd = etSubNewPsd.getText().toString().trim();
        if (TextUtils.isEmpty(oldPsd)) {
            ToastUtils.showShort(getString(R.string.please_input_old_psd));
            return;
        }
        if (TextUtils.isEmpty(newPsd)) {
            ToastUtils.showShort(getString(R.string.please_input_new_psd));
            return;
        }
        if (TextUtils.isEmpty(subNewPsd)) {
            ToastUtils.showShort(getString(R.string.please_input_new_psd_one_more));
            return;
        }
        if (!TextUtils.equals(newPsd, subNewPsd)) {
            ToastUtils.showShort(getString(R.string.input_new_psd_not_fit));
            return;
        }

        data = new ModifyPsdParam.Data();
        data.uid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        data.password = oldPsd;
        data.newword = subNewPsd;

        assert mPresenter != null;
        mPresenter.getToken();
    }

    @Override
    public void getToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            ModifyPsdParam params = new ModifyPsdParam();
            data.token = token;
            params.data = new Gson().toJson(data);

            assert mPresenter != null;
            mPresenter.modifyPsd(params);
        }
    }

    @Override
    public void successModify() {
        ToastUtils.showShort(getString(R.string.modify_success_to_login));
        SPUtils.getInstance(Constant.SHARED_NAME).clear();
        EventModel event = new EventModel();
        event.isLogout = true;
        event.isModify = true;
        RxBus.getInstance().post(event);
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
