package com.lightheart.sphr.doctor.module.contracts.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import com.lightheart.sphr.doctor.view.SlidingButtonView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-4-25.
 * Description : 好友以及好友申请列表adapter
 */

public class ContractsAdapter extends BaseQuickAdapter<ContractDocItem, ContractsAdapter.ContractsViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private String mType;
    private SlidingButtonView mMenu = null;
    private SlideItemListener mSlideItemListener;
    private final int mWidthPixels;

    //    @Inject
    public ContractsAdapter(Context context, int item_doc_contract, String type) {
        super(item_doc_contract, null);
        this.mContext = context;
        this.mType = type;
        mWidthPixels = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void convert(final ContractsViewHolder helper, final ContractDocItem item) {
        if (TextUtils.equals("APPLY", mType)) {
            helper.tvAccept.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals("ADDED", mType)) {
            helper.tvAccept.setVisibility(View.INVISIBLE);
        }
        helper.itemView.setSlidingEnable(true); // 管理员的条目也不能侧滑
        helper.rl_content.getLayoutParams().width = mWidthPixels;
        CircleImageView civContract = helper.getView(R.id.civContract);
        ImageLoaderUtils.display(mContext, civContract, item.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
        helper.setText(R.id.tvConName, TextUtils.isEmpty(item.getContName()) ? "" : item.getContName());
        // 删除
        helper.item_delete_tv.setOnClickListener(new View.OnClickListener() {
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
        helper.rl_content.setOnClickListener(new View.OnClickListener() {
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

        void itemClick(View view, int position, ContractDocItem item);// 条目点击

        void accept(View view, int position, ContractDocItem item);

        void deleteClick(View view, int position, ContractDocItem item); // 删除

    }

    class ContractsViewHolder extends BaseViewHolder {

        private SlidingButtonView itemView;
        private TextView item_delete_tv, tvAccept;
        private RelativeLayout rl_content;

        ContractsViewHolder(View itemView) {
            super(itemView);
            this.itemView = (SlidingButtonView) itemView;
            item_delete_tv = itemView.findViewById(R.id.contract_item_delete_tv);
            rl_content = (RelativeLayout) itemView.findViewById(R.id.item_content);
            tvAccept = (TextView) itemView.findViewById(R.id.tvAccept);
            ((SlidingButtonView) itemView).setSlidingButtonListener(ContractsAdapter.this);
        }
    }

}
