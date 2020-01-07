package com.guwu.common.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by admin  2020/1/7/17:14
 * Describe
 * 作者 洪亮 admin
 * 带滚动监听的scrollview
 */
public class GradientScrollView extends ScrollView {

    public interface ScrollViewListener {

        void onScrollChanged(GradientScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }

    private ScrollViewListener scrollViewListener = null;

    public GradientScrollView(Context context) {
        super(context);
    }

    public GradientScrollView(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
    }

    public GradientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

}
