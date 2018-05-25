package com.lightheart.sphr.doctor.module.contracts.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.QRcodeModel;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    @BindView(R.id.mainBar)
    AppBarLayout mainBar;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.zxingview)
    QRCodeView mQRCodeView;
    private static final String TAG = ScanActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_scan;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.scan, false, 0);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        assert vibrator != null;
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        QRcodeModel qRcodeModel = new Gson().fromJson(result, QRcodeModel.class);
        this.setResult(Activity.RESULT_OK, new Intent().putExtra("info", qRcodeModel));
        vibrate();
        this.finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}