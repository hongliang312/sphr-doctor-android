package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.RequestParams;
import com.lightheart.sphr.doctor.module.my.contract.MyHomePageContract;
import com.lightheart.sphr.doctor.module.my.presenter.MyHomePagePresenter;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-11.
 * Description : 医生主页
 */

public class MyHomePageActivity extends BaseActivity<MyHomePagePresenter> implements MyHomePageContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.clvHeadImage)
    CircleImageView clvHeadImage;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvHospital)
    TextView tvHospital;
    @BindView(R.id.tvOutNum)
    TextView tvOutNum;
    @BindView(R.id.tvOperatNum)
    TextView tvOperatNum;
    @BindView(R.id.tvGoodAt)
    TextView tvGoodAt;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.tvAddFriend)
    TextView tvAddFriend;
    private int duid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_home_page;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.my_page, false, 0);
        String mFlag = getIntent().getStringExtra("flag");
        duid = getIntent().getIntExtra("duid", 0);
        if (TextUtils.equals("CHECK", mFlag)) {
            tvAddFriend.setVisibility(View.GONE);
        } else if (TextUtils.equals("ADD", mFlag)) {
            tvAddFriend.setVisibility(View.VISIBLE);
        }

        assert mPresenter != null;
        mPresenter.loadDoc(duid);
    }

    @OnClick(R.id.tvAddFriend)
    public void onClick(View view) {
        RequestParams params = new RequestParams();
        params.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        params.contUid = this.duid;
        assert mPresenter != null;
        mPresenter.toAddFriend(params);
    }

    @Override
    public void setData(DoctorBean docInfo) {
        if (docInfo != null) {
            ImageLoaderUtils.display(this, clvHeadImage, docInfo.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
            tvName.setText(TextUtils.isEmpty(docInfo.getRealName()) ? "" : docInfo.getRealName());
            tvDepartment.setText(TextUtils.isEmpty(docInfo.getDepartmentName()) ? "" : docInfo.getDepartmentName());
            tvTitle.setText(TextUtils.isEmpty(docInfo.getTitleName()) ? "" : docInfo.getTitleName());
            tvHospital.setText(TextUtils.isEmpty(docInfo.getHospitalName()) ? "" : docInfo.getHospitalName());
            tvOutNum.setText(docInfo.getOutpatientNum() == 0 ? "" : docInfo.getOutpatientNum() + "");
            tvOperatNum.setText(docInfo.getOperationNum() == 0 ? "" : docInfo.getOperationNum() + "");
            tvGoodAt.setText(TextUtils.isEmpty(docInfo.getMajorIn()) ? getString(R.string.good_at) : getString(R.string.good_at) + docInfo.getMajorIn());
            tvDes.setText(TextUtils.isEmpty(docInfo.getSummary()) ? "" : docInfo.getSummary());
        }
    }

    @Override
    public void successAdd() {
        ToastUtils.showShort(getString(R.string.add_friend_hint));
        this.finish();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
