package com.lightheart.sphr.doctor.module.home.ui;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.guwu.common.recyclerview.OnRecyclerItemClickListener;
import com.guwu.common.utils.DisplayUtil;
import com.guwu.common.utils.ToastUtil;
import com.guwu.common.utils.WXTouchHelper;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.module.home.adapter.PhotoPublishAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class Main5Activity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.iv_qzone)
    ImageView ivQzone;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.recycler_photo)
    RecyclerView recyclerPhoto;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;


    private int limit = 9;//限制上传多少张图拍呢
    private List<String> imgSelected = new ArrayList<>();
    private PhotoPublishAdapter photoPublishAdapter;
    private ItemTouchHelper itemTouchHelper;

    private int judgeClickMargin;
    private int bottomItemHeight;
    private int lineSpace;
    private int leftMargin;
    private int starWidth;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main5;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        initVie();
        initList();
        fixBottom();
    }

    private void initList() {
        photoPublishAdapter = new PhotoPublishAdapter( this, imgSelected, limit );
        /**
         * 如果没有scrollView包裹 则不用传入scrollView
         */
        WXTouchHelper myCallBack = new WXTouchHelper( photoPublishAdapter, imgSelected, scrollView );
        itemTouchHelper = new ItemTouchHelper( myCallBack );
        itemTouchHelper.attachToRecyclerView( recyclerPhoto );
        recyclerPhoto.setAdapter( photoPublishAdapter );
        photoPublishAdapter.setData( imgSelected );

        recyclerPhoto.addOnItemTouchListener( new OnRecyclerItemClickListener( recyclerPhoto ) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {


                if (viewHolder.getAdapterPosition() == imgSelected.size()) {
                    selectPicture();
                } else {
                    ToastUtils.showShort( "图片查看器带开发" );
                }
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getAdapterPosition() != imgSelected.size()) { //关闭软件盘
                    itemTouchHelper.startDrag( viewHolder );
                }
            }

            @Override
            public void onOtherClick(MotionEvent e) {
                if (e.getY() > judgeClickMargin) {
                    int between = (int) e.getY() - judgeClickMargin;//判读触摸点与 bottom布局分界处的距离
                    int oneItem = (bottomItemHeight + lineSpace);//一个textview+一个分割线的高度
                    LogUtils.e( e.getY() + "---" + judgeClickMargin + "===" + oneItem );
                    if (between > 0 && between <= oneItem) {
                        //点击在第一个textview上 ---所在位置
                        ToastUtil.normal( "所在位置" );
                    } else if (between > oneItem && between <= 2 * oneItem) {
                        //点击在第二个textview上 ---谁可以看
                        ToastUtil.normal( "谁可以看" );
                    } else if (between > 2 * oneItem && between <= 3 * oneItem) {
                        //点击在第三个textview上 ---提醒谁看
                        ToastUtil.normal( "提醒谁看" );
                    } else if (between > 3 * oneItem && between <= 4 * oneItem && e.getX() >= leftMargin && e.getX() <= (starWidth + leftMargin)) {
                        //点击星星 同步到空间
                        ToastUtil.normal( "同步到空间" );
                    }
                }
            }
        } );


        myCallBack.setDragListener( new WXTouchHelper.DragListener() {
            @Override
            public void deleteState(boolean delete) {
                if (delete) {
                    tvDelete.setAlpha( 0.8f );
                    tvDelete.setText( "松手即可删除" );
                } else {
                    tvDelete.setAlpha( 0.5f );
                    tvDelete.setText( "拖到此处删除" );
                }
            }

            @Override
            public void dragState(boolean start) {
                if (start) {
                    tvDelete.setVisibility( View.VISIBLE );
                } else {
                    tvDelete.setVisibility( View.GONE );
                }
            }

            @Override
            public void clearView() {
                fixBottom();
            }

            @Override
            public void deleteOk() {
                //删除后重新计算图片选择数量
                limit = 9 - imgSelected.size();
            }
        } );
    }

    //选择图片
    private void selectPicture() {
        PictureSelector.create( this )
                .openGallery( PictureMimeType.ofImage() )
                .maxSelectNum( limit )
                .minSelectNum( 0 )
                .previewImage( true )// 是否可预览图片
                .enableCrop( false )// 是否裁剪 true or false
                .compress( true )
                .isCamera( true )
                .previewEggs( true )// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .selectionMode( PictureConfig.MULTIPLE )  //选择模式
                .forResult( PictureConfig.CHOOSE_REQUEST );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                images = PictureSelector.obtainMultipleResult( data );
                for (int i = 0; i < images.size(); i++) {
                    imgSelected.add( images.get( i ).getPath() );
                }
                if (limit > 0) {
                    limit = 9 - imgSelected.size();
                }
                photoPublishAdapter.notifyDataSetChanged();
                fixBottom();
            }
        }
    }

    private void initVie() {
        //textview 高度
        bottomItemHeight = getResources().getDimensionPixelSize( R.dimen.dp_1 );
        //textview之间分割线 高度
        lineSpace = (int) getResources().getDimension( R.dimen.dp_1 );
        //左边距
        leftMargin = (int) getResources().getDimension( R.dimen.dp_20 );
        //同步到控件 星星的宽度  图片大小为30dp 左右各留10dp 方便用户
        starWidth = (int) getResources().getDimension( R.dimen.dp_50 );
    }

    /**
     * 处理recyclerView下面的布局
     */
    private void fixBottom() {
        int row = photoPublishAdapter.getItemCount() / 3;
        row = (0 == photoPublishAdapter.getItemCount() % 3) ? row : row + 1;//少于3为1行
        row = (4 == row) ? 3 : row;//最多为三行

        int width = DisplayUtil.getScreenWidth( this );
        int itemWidth = (int) (width - getResources().getDimension( R.dimen.dp_60 )) / 3;//item宽高
        int itemSpace = (int) getResources().getDimension( R.dimen.dp_10 );//item间隔
        int marginTop = (getResources().getDimensionPixelSize( R.dimen.dp_120 )
                + itemWidth * row
                + itemSpace * (row - 1)
                + getResources().getDimensionPixelSize( R.dimen.dp_40 ));
        //用户判断 在每次fix底部布局高度后判断 注意要减去顶部edittext的高度
        judgeClickMargin = marginTop - getResources().getDimensionPixelSize( R.dimen.dp_100 );
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
        params.setMargins( 0, marginTop, 0, 0 );
        llBottom.setLayoutParams( params );
    }

}
