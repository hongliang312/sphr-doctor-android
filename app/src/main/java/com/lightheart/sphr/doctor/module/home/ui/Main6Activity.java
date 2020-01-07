package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guwu.common.recyclerview.RecyItemTouchHelperCallback;
import com.guwu.common.utils.ToastUtil;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.Main6DataBean;
import com.lightheart.sphr.doctor.module.home.adapter.Main6Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Main6Activity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.main_list)
    RecyclerView mainList;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.root_layout)
    CoordinatorLayout rootLayout;

    private List<Main6DataBean> data;
    private Main6Adapter mainAdapter;
    private long mBackPressed;
    private static final int TIME_INTERVAL = 2000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main6 ;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        titleName.setText("一个快小饼干");
        data = new ArrayList<>();

        initData();

        mainAdapter = new Main6Adapter(R.layout.main_item);
        mainAdapter.setOnItemClickListener( this );
        mainList.setAdapter( mainAdapter );
        mainAdapter.setNewData(data);
        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback( mainAdapter );
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mainList);


    }
    private void initData() {
        data.add(new Main6DataBean(R.mipmap.home_camera, "uCrop头像裁剪", Main3Activity.class));
        data.add(new Main6DataBean(R.mipmap.home_demo4, "仿ios加载框", Main4Activity.class));
        data.add(new Main6DataBean(R.mipmap.home_demo7, "微信发朋友圈拖动删", Main5Activity.class));
        data.add(new Main6DataBean(R.mipmap.ic_launcher, "地址选择器", Main7Activity.class));
        data.add(new Main6DataBean(R.mipmap.ic_launcher, "仿支付宝首页顶部伸缩滑动/中间层下拉刷新", Main8Activity.class));
        data.add(new Main6DataBean(R.mipmap.ic_launcher, "仿京东顶部渐变/自定义指示器/viewpager3D效果/瀑布流", Main9Activity.class));

        for (int i = 0; i < 20; i++) {
            data.add(new Main6DataBean(R.mipmap.home_more, "待续", Main5Activity.class));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity( new Intent( this,data.get( position ).getActivity() ) );
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            ToastUtil.snack(rootLayout, "再按一次退出");
        }
        mBackPressed = System.currentTimeMillis();
    }
}
