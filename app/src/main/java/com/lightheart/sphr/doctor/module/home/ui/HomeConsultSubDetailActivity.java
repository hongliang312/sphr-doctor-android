package com.lightheart.sphr.doctor.module.home.ui;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.ConsultingReplyRequestParams;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetailRequestParams;
import com.lightheart.sphr.doctor.module.home.adapter.HomeConsultSubDetailAdapter;
import com.lightheart.sphr.doctor.module.home.contract.HomeConsultSubDetailContract;
import com.lightheart.sphr.doctor.module.home.presenter.HomeConsultSubDetailPresenter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * 在线咨询和电话咨询详情页，分待完成和已完成
 */
public class HomeConsultSubDetailActivity extends BaseActivity<HomeConsultSubDetailPresenter> implements HomeConsultSubDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.tvPatientName)
    TextView tvPatientName;
    @BindView(R.id.tvPatientRecords)
    TextView tvPatientRecords;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvPhoneTime)
    TextView tvPhoneTime;
    @BindView(R.id.tvLoadicture)
    RecyclerView tvLoadicture;
    @BindView(R.id.tvLineaLayout)
    LinearLayout tvLineaLayout;
    @BindView(R.id.tvLinea)
    LinearLayout tvLinea;
    @BindView(R.id.tvLayout)
    LinearLayout tvLayout;
    @BindView(R.id.FeedBack)
    TextView feedback;
    @BindView(R.id.tvText)
    TextView tvText;
    private List<HomeConsultSubDetail.ImgsBean> consultSublist = new ArrayList<>();
    private int id;
    private String consultType;
    private String consultStatus;
    private String tvPatientNamee;
    private HomeConsultSubDetail content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_consult_subdetail;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        consultType = getIntent().getStringExtra("consult_type");
        consultStatus = getIntent().getStringExtra("consult_status");
        tvPatientNamee = getIntent().getStringExtra("tvPatientName");
        id = getIntent().getIntExtra("id", 0);
        tvText.setText(transformString(R.string.war_prompt, 0, 4, R.color.theme_color));

        HomeConsultSubDetailRequestParams subDetailRequestParams = new HomeConsultSubDetailRequestParams();
        subDetailRequestParams.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
        subDetailRequestParams.id = id;

        assert mPresenter != null;
        if (TextUtils.equals("TEL", consultType)) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.tel_online, false, 0);
            mPresenter.loadTelDetailsData(subDetailRequestParams);
        } else if (TextUtils.equals("ONLINE", consultType)) {
            initToolbar(mToolbar, mTitleTv, mBtSub, R.string.consult_online, false, 0);
            if ("SER_CST_S_ING".equals(consultStatus)) {
                tvLineaLayout.setVisibility(View.VISIBLE);
                tvLinea.setVisibility(View.GONE);
                tvLayout.setVisibility(View.GONE);
            } else {
                tvLineaLayout.setVisibility(View.GONE);
                tvLinea.setVisibility(View.VISIBLE);
                tvLayout.setVisibility(View.VISIBLE);
            }
            mPresenter.loadHomeConsultSubDetailData(subDetailRequestParams);
        }
    }
    @OnClick({R.id.Submit,R.id.tvPatientRecords})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Submit:
                ConsultingReplyRequestParams replyConsultingbean = new ConsultingReplyRequestParams();
                replyConsultingbean.id = id;
                replyConsultingbean.content = feedback.getText().toString().trim();

                if (TextUtils.isEmpty(replyConsultingbean.content)) {
                    ToastUtils.showShort(getString(R.string.feed_back_reply));
                    return;
                }
                assert mPresenter != null;
                mPresenter.replyConsult(replyConsultingbean);
                break;
            case R.id.tvPatientRecords:
                Intent intent = new Intent(HomeConsultSubDetailActivity.this, PatientRecordsActivity.class);
                intent.putExtra("id", content.getPuid());
                startActivity(intent);
          }
    }

    @Override
    public void setHomeConsultSubDetailData(final HomeConsultSubDetail content) {
        this.content=content;
        if (content != null) {
            consultSublist.clear();
            consultSublist.addAll(content.getImgs());
            tvLoadicture.setLayoutManager(new GridLayoutManager(this, 3));
            HomeConsultSubDetailAdapter subDetailAdapter;
            subDetailAdapter = new HomeConsultSubDetailAdapter(this, consultSublist);
            tvLoadicture.setAdapter(subDetailAdapter);
            tvDescription.setText(content.getContent());
            tvPhoneTime.setText(TimeUtils.millis2String(consultSublist.get(0).getCreateTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)));
            tvPatientName.setText(tvPatientNamee);
        }
    }
    @Override
    public void successReply() {
        ToastUtils.showShort("提交成功！");
        this.finish();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
    private SpannableString transformString(int s, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(getString(s));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(color));
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;

    }
}
