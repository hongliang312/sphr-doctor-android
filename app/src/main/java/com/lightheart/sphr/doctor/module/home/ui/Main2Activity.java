package com.lightheart.sphr.doctor.module.home.ui;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ielse.imagewatcher.ImageWatcher;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.module.home.adapter.MessageAdapter;
import com.lightheart.sphr.doctor.module.home.photon_utils.CustomLoadingUIProvider2;
import com.lightheart.sphr.doctor.module.home.photon_utils.Data;
import com.lightheart.sphr.doctor.module.home.photon_utils.DecorationLayout;
import com.lightheart.sphr.doctor.module.home.photon_utils.GlideSimpleLoader;
import com.lightheart.sphr.doctor.module.home.photon_utils.MessagePicturesLayout;
import com.lightheart.sphr.doctor.module.home.photon_utils.SpaceItemDecoration;
import com.lightheart.sphr.doctor.module.home.photon_utils.Utils;

import java.util.List;

import butterknife.BindView;

public class Main2Activity extends BaseActivity implements MessagePicturesLayout.Callback {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mSubmit;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

    private ImageWatcherHelper iwHelper;
    private RecyclerView vRecycler;
    private MessageAdapter adapter;
    private DecorationLayout layDecoration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mSubmit, R.string.photo, false,0);
        layDecoration = new DecorationLayout(this);
        vRecycler = (RecyclerView) findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        vRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(Data.getGif());

        vRecycler = (RecyclerView) findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        vRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(Data.getGif());

        iwHelper = ImageWatcherHelper.with(this, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
//                .setTranslucentStatus(false ? Utils.calcStatusBarHeight(this) : 0) // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
                .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
                .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
                    @Override
                    public void onPictureLongPress(ImageView v, Uri uri, int pos) {
                        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
                        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
                    @Override
                    public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position, Uri uri, float animatedValue, int actionTag) {
                        Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri + "][" + animatedValue + "][" + actionTag + "]");
                    }

                    @Override
                    public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
                        if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
//                            Toast.makeText(getApplicationContext(), "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
//                            Toast.makeText(getApplicationContext(), "退出了查看大图", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setOtherView(layDecoration)
                .addOnPageChangeListener(layDecoration)
                .setLoadingUIProvider(new CustomLoadingUIProvider2()); // 自定义LoadingUI

        layDecoration.attachImageWatcher(iwHelper);
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        iwHelper.show(i, imageGroupList, urlList);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
