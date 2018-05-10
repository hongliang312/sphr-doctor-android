package com.lightheart.sphr.doctor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;

/**
 * Created by hezc on 2017-4-13.
 * recyclerView 侧滑
 */

public class SlidingButtonView extends HorizontalScrollView {
    private TextView transfer_tv, delete_tv;
    private int mScrollWidth;
    private IonSlidingButtonListener mIonSlidingButtonListener;
    private Boolean isOpen = false;
    private Boolean once = false;
    private boolean slidingEnable = true;

    public SlidingButtonView(Context context) {
        this(context, null);
    }

    public SlidingButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
//            transfer_tv = (TextView) findViewById(R.id.member_item_transfer_tv);
            delete_tv = (TextView) findViewById(R.id.contract_item_delete_tv);
            once = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
//            mScrollWidth = transfer_tv.getWidth()+ delete_tv.getWidth();
            mScrollWidth = delete_tv.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isSlidingEnable()) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    if (mIonSlidingButtonListener != null)
                        mIonSlidingButtonListener.onDownOrMove(this);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    changeScrollx();
                    return true;
                default:
                    break;
            }
            return super.onTouchEvent(ev);
        } else {
            return true;// 禁止滑动了,事件拦截了
        }

    }

    /**
     * 按滚动条被拖动距离判断关闭或打开菜单
     */
    public void changeScrollx() {
        if (getScrollX() >= (mScrollWidth / 2)) {
            this.smoothScrollTo(mScrollWidth, 0);
            isOpen = true;
            if (mIonSlidingButtonListener != null)
                mIonSlidingButtonListener.onMenuIsOpen(this);
        } else {
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(mScrollWidth, 0);
        isOpen = true;
        if (mIonSlidingButtonListener != null)
            mIonSlidingButtonListener.onMenuIsOpen(this);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (!isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    public boolean isSlidingEnable() {
        return slidingEnable;
    }

    public void setSlidingEnable(boolean slidingEnable) {
        this.slidingEnable = slidingEnable;
    }

    public void setSlidingButtonListener(IonSlidingButtonListener listener) {
        mIonSlidingButtonListener = listener;
    }

    public interface IonSlidingButtonListener {
        void onMenuIsOpen(View view);

        void onDownOrMove(SlidingButtonView slidingButtonView);
    }
}