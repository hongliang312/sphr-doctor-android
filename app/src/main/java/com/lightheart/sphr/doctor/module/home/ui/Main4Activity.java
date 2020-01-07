package com.lightheart.sphr.doctor.module.home.ui;


import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.utils.RxTimer;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import butterknife.BindView;

public class Main4Activity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;
    private Object loadingArticle;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main4;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        tv.setOnClickListener( v -> {
            loadingDialog = new LoadingDialog( this );
            loadingDialog.setLoadingText( "登录中" )
                    .setFailedText(  "登录失败")
                    .setInterceptBack( true )
                    .setLoadSpeed( LoadingDialog.Speed.SPEED_TWO )
                    .setRepeatCount( 0 )
                    .setShowTime( 3000 )
                    .setLoadStyle(  LoadingDialog.STYLE_LINE )
                    .show();

            RxTimer rxTimer = new RxTimer();
            rxTimer.timer( 3000, this::save );

        } );
    }

    private void save(long l) {
        loadingDialog.loadSuccess();
    }
}
