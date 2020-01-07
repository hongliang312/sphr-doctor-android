package com.lightheart.sphr.doctor.module.my.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.AreaModel;
import com.lightheart.sphr.doctor.module.my.adapter.AreaAdapter;
import com.lightheart.sphr.doctor.module.my.contract.AreaContract;
import com.lightheart.sphr.doctor.module.my.presenter.AreaPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-22.
 * Description : 选择科室页面
 */

public class DepartmentActivity extends BaseActivity<AreaPresenter> implements AreaContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.rvArea)
    RecyclerView rvDepartment;
    @Inject
    AreaAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.distract_select, false, 0);

        rvDepartment.setLayoutManager(new LinearLayoutManager(this));
        rvDepartment.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        assert mPresenter != null;
        mPresenter.loadDepartmentData();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final AreaModel item = (AreaModel) adapter.getItem(position);
        assert item != null;
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.setContentView(R.layout.dialog_input_department);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.setCanceledOnTouchOutside(false);
        TextView title = (TextView) dialog.findViewById(R.id.tvTitle);
        final TextInputEditText etDepartment = (TextInputEditText) dialog.findViewById(R.id.etDepartment);
        TextView button_cancel = (TextView) dialog.findViewById(R.id.button_cancel);
        TextView button_perfect = (TextView) dialog.findViewById(R.id.button_perfect);
        title.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button_perfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etDepartment.getText().toString().trim())) {
                    setResult(Activity.RESULT_OK, new Intent().putExtra("departmentId", item.getId())
                            .putExtra("departmentName", etDepartment.getText().toString().trim()));
                    dialog.dismiss();
                    finish();
                } else {
                    ToastUtils.showShort(getString(R.string.please_input_child_department_name));
                }
            }
        });
    }

    @Override
    public void setDepartment(List<AreaModel> departments) {
        if (departments != null && departments.size() > 0)
            mAdapter.setNewData(departments);
        else
            initEmptyView(mAdapter, rvDepartment);
    }

    @Override
    public void setAreas(List<AreaModel> titles) {
    }

    @Override
    public void setChildAreas(List<AreaModel> childAreas) {
    }
}
