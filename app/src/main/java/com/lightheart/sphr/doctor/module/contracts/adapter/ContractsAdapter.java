package com.lightheart.sphr.doctor.module.contracts.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import com.lightheart.sphr.doctor.view.SlidingButtonView;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-4-25.
 * Description : 好友以及好友申请列表adapter
 */

public class ContractsAdapter extends BaseQuickAdapter<DoctorBean, ContractsAdapter.ContractsViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private String mType;
    private SlidingButtonView mMenu = null;
    private SlideItemListener mSlideItemListener;
    private int mWidthPixels;

    @Inject
    public ContractsAdapter() {
        super(R.layout.item_doc_contract, null);
    }

    public void initData(Context context, String type) {
        this.mContext = context;
        this.mType = type;
        mWidthPixels = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void convert(final ContractsViewHolder helper, final DoctorBean item) {
        if (TextUtils.equals("APPLY", mType)) {
            helper.tvAccept.setVisibility(View.VISIBLE);
            helper.itemView.setSlidingEnable(true);
            helper.setText(R.id.tvConName, TextUtils.isEmpty(item.getContName()) ? "" : item.getContName());
            helper.tvHospital.setVisibility(View.GONE);
        } else if (TextUtils.equals("ADDED", mType)) {
            helper.tvAccept.setVisibility(View.INVISIBLE);
            helper.itemView.setSlidingEnable(true);
            helper.setText(R.id.tvConName, TextUtils.isEmpty(item.getContName()) ? "" : item.getContName());
            helper.tvHospital.setVisibility(View.GONE);
        } else if (TextUtils.equals("SEARCH", mType)) {
            helper.tvAccept.setVisibility(View.INVISIBLE);
            helper.itemView.setSlidingEnable(false);// 搜索的医生列表不能滑动
            helper.setText(R.id.tvConName, TextUtils.isEmpty(item.getRealName()) ? "" : item.getRealName() + " · " + (TextUtils.isEmpty(item.getTitleName()) ? "" : item.getTitleName()));
            helper.tvHospital.setVisibility(View.VISIBLE);
            helper.tvHospital.setText(TextUtils.isEmpty(item.getHospitalName()) ? "" : item.getHospitalName());
        }
        helper.rl_content.getLayoutParams().width = mWidthPixels;
        CircleImageView civContract = helper.getView(R.id.civContract);
        ImageLoaderUtils.display(mContext, civContract, item.getImgUrl(), R.drawable.bg_grey, R.mipmap.ic_user);

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

        void itemClick(View view, int position, DoctorBean item);// 条目点击

        void accept(View view, int position, DoctorBean item);

        void deleteClick(View view, int position, DoctorBean item); // 删除

    }

    class ContractsViewHolder extends BaseViewHolder {

        private SlidingButtonView itemView;
        private TextView item_delete_tv, tvAccept, tvHospital;
        private RelativeLayout rl_content;

        ContractsViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            this.itemView = (SlidingButtonView) itemView;
            item_delete_tv = itemView.findViewById(R.id.contract_item_delete_tv);
            rl_content = (RelativeLayout) itemView.findViewById(R.id.item_content);
            tvAccept = (TextView) itemView.findViewById(R.id.tvAccept);
            tvHospital = (TextView) itemView.findViewById(R.id.tvHospital);
            ((SlidingButtonView) itemView).setSlidingButtonListener(ContractsAdapter.this);
        }
    }

}
