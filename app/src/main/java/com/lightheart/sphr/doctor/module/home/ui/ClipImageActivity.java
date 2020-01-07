package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.view.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 头像裁剪Activity
 */
public class ClipImageActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ClipImageActivity";

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.clipViewLayout1)
    ClipViewLayout clipViewLayout1;
    @BindView(R.id.clipViewLayout2)
    ClipViewLayout clipViewLayout2;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.bt_ok)
    TextView btnOk;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clip_image;
    }

    @Override
    protected void initInjector() {
    }

    /**
     * 初始化组件
     */
    public void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.clipping, false, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clipViewLayout1.setVisibility(View.VISIBLE);
        clipViewLayout2.setVisibility(View.GONE);
        //设置图片资源
        clipViewLayout1.setImageSrc(getIntent().getData());
    }

    @OnClick({R.id.btn_cancel, R.id.bt_ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:
                generateUriAndReturn();
                break;
        }
    }


    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        zoomedCropBitmap = clipViewLayout1.clip();
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
