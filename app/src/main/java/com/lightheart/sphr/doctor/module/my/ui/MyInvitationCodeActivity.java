package com.lightheart.sphr.doctor.module.my.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-12.
 * Description :我的二维码页面
 */

public class MyInvitationCodeActivity extends BaseActivity {

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
    @BindView(R.id.tvHospital)
    TextView tvHospital;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.tvInviteCode)
    TextView tvInviteCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitation_code;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.my_invitation_code, false, 0);
        DoctorBean docInfo = (DoctorBean) getIntent().getSerializableExtra("docInfo");
        if (docInfo != null) {
            ImageLoaderUtils.display(this, clvHeadImage, docInfo.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
            tvName.setText(TextUtils.isEmpty(docInfo.getRealName()) ? "" : docInfo.getRealName());
            tvHospital.setText(TextUtils.isEmpty(docInfo.getHospitalName()) ? "" : docInfo.getHospitalName());
            tvInviteCode.setText(TextUtils.isEmpty(docInfo.getInviteCode()) ? getString(R.string.invitation_code) : getString(R.string.invitation_code) + docInfo.getInviteCode());
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
