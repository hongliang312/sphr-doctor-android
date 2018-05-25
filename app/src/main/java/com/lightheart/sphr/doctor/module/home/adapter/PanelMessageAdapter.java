package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PanelMessageModel;
import com.lightheart.sphr.doctor.view.SlidingButtonView;

import javax.inject.Inject;

/**
 * Created by fucp on 2018-4-25.
 * Description : 好友以及好友申请列表adapter
 */

public class PanelMessageAdapter extends BaseQuickAdapter<PanelMessageModel, PanelMessageAdapter.PanelMesViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private SlidingButtonView mMenu = null;
    private SlideItemListener mSlideItemListener;
    private int mWidthPixels;

    @Inject
    public PanelMessageAdapter() {
        super(R.layout.item_panel_message, null);
    }

    public void init(Context context) {
        this.mContext = context;
        mWidthPixels = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void convert(final PanelMesViewHolder helper, final PanelMessageModel item) {

        helper.setText(R.id.tvMessage, TextUtils.isEmpty(item.getContent()) ? "" : item.getContent());
        helper.ll_content.getLayoutParams().width = mWidthPixels;

        // 删除
        helper.tvDeleteMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlideItemListener != null) {
                    int adapterPosition = helper.getAdapterPosition();
                    mSlideItemListener.deleteClick(v, adapterPosition, item);
                }
            }
        });
        // 开启任务
        helper.tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlideItemListener != null) {
                    int adapterPosition = helper.getAdapterPosition();
                    mSlideItemListener.accept(v, adapterPosition, item);
                }
            }
        });
        helper.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int adapterPosition = helper.getAdapterPosition();
                    mSlideItemListener.itemClick(v, adapterPosition, item);
                }
            }
        });
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        return mMenu != null;
    }

    public void setOnSlideItemListener(SlideItemListener listener) {
        mSlideItemListener = listener;
    }

    public interface SlideItemListener {

        void itemClick(View view, int position, PanelMessageModel item);// 条目点击

        void accept(View view, int position, PanelMessageModel item);

        void deleteClick(View view, int position, PanelMessageModel item); // 删除

    }

    class PanelMesViewHolder extends BaseViewHolder {

        private SlidingButtonView itemView;
        private TextView tvMessage, tvAccept, tvDeleteMes;
        private LinearLayout ll_content;

        PanelMesViewHolder(View itemView) {
            super(itemView);
            this.itemView = (SlidingButtonView) itemView;
            tvMessage = itemView.findViewById(R.id.tvMessage);
            ll_content = (LinearLayout) itemView.findViewById(R.id.item_content);
            tvAccept = (TextView) itemView.findViewById(R.id.tvAccept);
            tvDeleteMes = (TextView) itemView.findViewById(R.id.contract_item_delete_tv);
            ((SlidingButtonView) itemView).setSlidingButtonListener(PanelMessageAdapter.this);
        }
    }

}
