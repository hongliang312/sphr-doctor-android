package com.lightheart.sphr.doctor.module.home.adapter;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.Main6DataBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by admin  2020/1/7/14:59
 * Describe
 * 作者 洪亮 admin
 */
public class Main6Adapter extends BaseQuickAdapter<Main6DataBean, BaseViewHolder> {

    private List<Main6DataBean> data;

    private boolean mFirstOnlyEnable = true;
    private boolean mOpenAnimationEnable = true;
    private BaseAnimation mSelectAnimation = new SlideInLeftAnimation();//由小到大动画
    private int mLastPosition = -1;
    private Interpolator mInterpolator = new LinearInterpolator();


    public Main6Adapter(int RestId) {
        super( RestId );
    }

    @Override
    protected void convert(BaseViewHolder helper, Main6DataBean item) {
        ImageLoaderUtils.display( mContext, helper.getView( R.id.iv_logo ), item.getImgRes() );
        helper.setText( R.id.tv_text, item.getContent() );
        helper.setText( R.id.tv_name, "Demo" + (helper.getAdapterPosition() + 1) );
        addAnimation( helper );
    }


    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (!mFirstOnlyEnable || holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;

                animation = mSelectAnimation;

                for (Animator anim : animation.getAnimators( holder.itemView )) {
                    startAnim( anim, holder.getLayoutPosition() );
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    protected void startAnim(Animator anim, int index) {
        anim.setDuration( 300 ).start();
        anim.setInterpolator( mInterpolator );
    }

}
