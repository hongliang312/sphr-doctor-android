package com.lightheart.sphr.doctor.module.my;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.module.my.contract.MyContract;
import com.lightheart.sphr.doctor.module.my.presenter.MyPresenter;
import com.lightheart.sphr.doctor.module.my.ui.AuthenticationActivity;
import com.lightheart.sphr.doctor.module.my.ui.FeedBackActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;
import com.lightheart.sphr.doctor.module.my.ui.MyInvitationCodeActivity;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-4-19.
 * Description :我的页面
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.clvHeadImage)
    CircleImageView clvHeadImage;
    @BindView(R.id.rlDcoInfo)
    RelativeLayout rlDcoInfo;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTutor)
    TextView tvTutor;
    @BindView(R.id.tvAuth)
    TextView tvAuth;
    @BindView(R.id.tvMyHomePage)
    TextView tvMyHomePage;
    @BindView(R.id.tvFeedBadk)
    TextView tvFeedBadk;
    @BindView(R.id.tvMyInvitationCode)
    TextView tvMyInvitationCode;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    private DoctorBean doctorBean;

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
        assert mPresenter != null;
        mPresenter.loadDocData();
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public void setDocIndo(DoctorBean docIndo) {
        if (docIndo != null) {
            doctorBean = new DoctorBean();
            doctorBean = docIndo;
            ImageLoaderUtils.display(getActivity(), clvHeadImage, docIndo.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
            tvUserName.setText(TextUtils.isEmpty(docIndo.getRealName()) ? "" : docIndo.getRealName());
            tvTitle.setText(TextUtils.isEmpty(docIndo.getTitleName()) ? "" : docIndo.getTitleName());
            tvTutor.setText(TextUtils.isEmpty(docIndo.getDepartmentName()) ? "" : docIndo.getDepartmentName());
            tvAuth.setText(TextUtils.equals("USR_CERT_S_SUC", docIndo.getCertStatus()) ? getString(R.string.certify_suc) :
                    (TextUtils.equals("USR_CERT_S_IN", docIndo.getCertStatus()) ? getString(R.string.certify_ing) :
                            (TextUtils.equals("USR_CERT_S_UN", docIndo.getCertStatus()) ? getString(R.string.to_certify) :
                                    (TextUtils.equals("USR_CERT_S_FAL", docIndo.getCertStatus()) ? getString(R.string.certify_fail) : ""))));
        }
    }

    @OnClick({R.id.clvHeadImage, R.id.rlDcoInfo, R.id.tvAuth, R.id.tvMyHomePage, R.id.tvFeedBadk, R.id.tvMyInvitationCode, R.id.tvSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clvHeadImage:
            case R.id.rlDcoInfo:
                ToastUtils.showShort("进入个人资料页面");
                break;
            case R.id.tvAuth:
                if (TextUtils.equals("USR_CERT_S_UN", doctorBean.getCertStatus()) || TextUtils.equals("USR_CERT_S_FAL", doctorBean.getCertStatus())) {
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                }
                break;
            case R.id.tvMyHomePage:
                startActivity(new Intent(getActivity(), MyHomePageActivity.class).putExtra("duid", doctorBean.getId()).putExtra("flag", "CHECK"));
                break;
            case R.id.tvFeedBadk:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.tvMyInvitationCode:
                if (TextUtils.equals("USR_CERT_S_UN", doctorBean.getCertStatus()) || TextUtils.equals("USR_CERT_S_FAL", doctorBean.getCertStatus())) {
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MyInvitationCodeActivity.class).putExtra("docInfo", doctorBean));
                }
                break;
            case R.id.tvSetting:

                break;
        }
    }

}
