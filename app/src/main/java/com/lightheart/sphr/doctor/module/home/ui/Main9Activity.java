package com.lightheart.sphr.doctor.module.home.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bambootang.viewpager3d.BambooFlowViewPager;
import com.bambootang.viewpager3d.ClipView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.guwu.common.adapter.PublicFragmentPagerAdapter;
import com.guwu.common.utils.DisplayUtil;
import com.guwu.common.utils.ToastUtil;
import com.guwu.common.widgets.GradientScrollView;
import com.guwu.common.widgets.PageIndicator;
import com.jaeger.library.StatusBarUtil;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.GridBean;
import com.lightheart.sphr.doctor.module.home.adapter.FootListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class Main9Activity extends BaseActivity implements GradientScrollView.ScrollViewListener {


    int[] imgIds = {R.mipmap.img_001, R.mipmap.img_002, R.mipmap.img_003, R.mipmap.img_004};
    @SuppressLint("UseSparseArrays")
    HashMap<Integer, ClipView> imageViewList = new HashMap<>();
    @BindView(R.id.fvp_pagers)
    BambooFlowViewPager fvpPagers;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.scrollView)
    GradientScrollView scrollView;
    @BindView(R.id.status_bar_fix)
    View statusBarFix;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    PageIndicator indicator;
    @BindView(R.id.demo_list)
    RecyclerView demoList;
    private int height;
    private PublicFragmentPagerAdapter indicatorAdapter;
    private String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main9;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        initBanner();
        initViewpager();
        initViewpager3D();
        initListener();
        initList();
        StatusBarUtil.setTranslucentForImageView( this, 50, null );
        statusBarFix.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getStatusBarHeight( this ) ) );
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params.height = DisplayUtil.getScreenWidth( this ) * 10 / 23;
        banner.setLayoutParams( params );
    }

    private void initList() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add( i + "" );
        }
        FootListAdapter footListAdapter = new FootListAdapter( this, data );
        demoList.setAdapter( footListAdapter );
        footListAdapter.setData( data );
        demoList.setNestedScrollingEnabled( false );
    }


    private void initListener() {
        scrollView.setScrollViewListener( this );
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeOnGlobalLayoutListener( this );
                height = banner.getHeight() - llTitle.getHeight();
            }
        } );


        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setSelection( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
    }


    private void initViewpager3D() {
        fvpPagers.setAdapter( pagerAdapter );
        fvpPagers.setOffscreenPageLimit( imgIds.length );
        fvpPagers.setCurrentItem( 1 );
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return imgIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView( (View) object );
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ClipView clipView;
            if (imageViewList.containsKey( position )) {
                clipView = imageViewList.get( position );
            } else {
                clipView = (ClipView) View.inflate( Main9Activity.this, R.layout.item_image, null );
                ImageView imageView = clipView.findViewById( R.id.iv_img );
                TextView tvTitle = clipView.findViewById( R.id.tv_title );
                imageView.setImageResource( imgIds[position] );
                imageViewList.put( position, clipView );
            }
            container.addView( clipView );
            clipView.setOnClickListener( v -> {
                ToastUtil.normal( "点击了" + (position + 1) );
                fvpPagers.setCurrentItem( position, true );
            } );
            return clipView;
        }
    };


    private void initViewpager() {
        //模拟数据 一般是一个数据源根据每页显示数量再拆分这里因模拟数据直接拆分两页 indicator也直接设置为2
        List<GridBean> data1 = new ArrayList<>();
        List<GridBean> data2 = new ArrayList<>();
        List<GridBean> data3 = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            data1.add( new GridBean( "分类" + i ) );
        }
        for (int i = 9; i <= 16; i++) {
            data2.add( new GridBean( "分类" + i ) );
        }
        for (int i = 17; i <= 24; i++) {
            data3.add( new GridBean( "分类" + i ) );
        }

        indicator.setCount( 3 );
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add( GridFragment.newInstance( data1 ) );
        list_fragment.add( GridFragment.newInstance( data2 ) );
        list_fragment.add( GridFragment.newInstance( data3 ) );
        indicatorAdapter = new PublicFragmentPagerAdapter( getSupportFragmentManager(), list_fragment );
        viewPager.setAdapter( indicatorAdapter );
    }

    private void initBanner() {
        List<Integer> imgs = new ArrayList<>();
        imgs.add( R.mipmap.banner_1 );
        imgs.add( R.mipmap.banner_2 );
        imgs.add( R.mipmap.banner_3 );
        imgs.add( R.mipmap.banner_4 );
        banner.setHorizontalScrollBarEnabled( false );
        banner.setPages( (CBViewHolderCreator<LocalImageHolderView>) LocalImageHolderView::new, imgs )
                .setPageIndicator( new int[]{R.drawable.shape_banner_no, R.drawable.shape_banner_yes} )
                .setOnItemClickListener( position -> {
                } )
                .setPageIndicatorAlign( ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL ).startTurning( 2000 );
    }

    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView( context );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource( data );
        }
    }

    //滑动监听 控制状态栏变化
    @Override
    public void onScrollChanged(GradientScrollView scrollView, int x, int y, int oldx, int oldy) {
        Drawable search_white = ContextCompat.getDrawable( this, R.mipmap.icon_search_white );
        Drawable search_black = ContextCompat.getDrawable( this, R.mipmap.icon_search_black );
        // 这一步必须要做,否则不会显示.
        search_white.setBounds( 0, 0, search_white.getMinimumWidth(), search_white.getMinimumHeight() );
        // 这一步必须要做,否则不会显示.
        search_black.setBounds( 0, 0, search_black.getMinimumWidth(), search_black.getMinimumHeight() );
        if (y <= 0) {
            llTitle.setBackgroundColor( Color.argb( (int) 0, 255, 255, 255 ) );
            rlLeft.setBackgroundResource( R.drawable.oval_shadow );
            rlRight.setBackgroundResource( R.drawable.oval_shadow );
            ivLeft.setImageResource( R.mipmap.icon_ewm );
            ivRight.setImageResource( R.mipmap.icon_ewm );
            tvSearch.setBackgroundResource( R.drawable.rec_shadow_r25 );
            tvSearch.setCompoundDrawables( search_white, null, null, null );
            tvSearch.setCompoundDrawablePadding( (int) getResources().getDimension( R.dimen.dp_5 ) );
            tvSearch.setHintTextColor( this.getResources().getColor( R.color.white ) );
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            llTitle.setBackgroundColor( Color.argb( (int) alpha, 255, 255, 255 ) );
            if (y > 0 && y <= height / 2) {
                rlLeft.setBackgroundResource( R.drawable.oval_shadow );
                rlRight.setBackgroundResource( R.drawable.oval_shadow );
                ivLeft.setImageResource( R.mipmap.icon_ewm );
                ivRight.setImageResource( R.mipmap.icon_ewm );
                tvSearch.setBackgroundResource( R.drawable.rec_shadow_r25 );
                tvSearch.setCompoundDrawables( search_white, null, null, null );
                tvSearch.setCompoundDrawablePadding( (int) getResources().getDimension( R.dimen.dp_5 ) );
                tvSearch.setHintTextColor( this.getResources().getColor( R.color.white ) );
            } else {
                rlLeft.setBackground( null );
                rlRight.setBackground( null );
                ivLeft.setImageResource( R.mipmap.icon_ewm_black );
                ivRight.setImageResource( R.mipmap.icon_ewm_black );
                tvSearch.setBackgroundResource( R.drawable.rec_gray_r25 );
                tvSearch.setCompoundDrawables( search_black, null, null, null );
                tvSearch.setCompoundDrawablePadding( (int) getResources().getDimension( R.dimen.dp_5 ) );
                tvSearch.setHintTextColor( this.getResources().getColor( R.color.black ) );
            }
        } else {
            llTitle.setBackgroundColor( Color.argb( (int) 255, 255, 255, 255 ) );
            rlLeft.setBackground( null );
            rlRight.setBackground( null );
            ivLeft.setImageResource( R.mipmap.icon_ewm_black );
            ivRight.setImageResource( R.mipmap.icon_ewm_black );
            tvSearch.setBackgroundResource( R.drawable.rec_gray_r25 );
            tvSearch.setCompoundDrawables( search_black, null, null, null );
            tvSearch.setCompoundDrawablePadding( (int) getResources().getDimension( R.dimen.dp_5 ) );
            tvSearch.setHintTextColor( this.getResources().getColor( R.color.black ) );
        }
    }


}
