package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TextBean;

import java.util.List;

/**
 * Created by admin  2019/11/26/15:55
 * Describe
 * 作者 洪亮 admin
 */
public class MineWalletAdapter extends BaseQuickAdapter<TextBean, BaseViewHolder> {

    public MineWalletAdapter(int layoutResId, List list) {
        super(layoutResId, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextBean item) {
        helper.setText(R.id.tv_account_earnings, TextUtils.isEmpty(item.getTitle()) ? "" : item.getTitle());
        helper.setText(R.id.tvTime, TextUtils.isEmpty(item.getName()) ? "" : item.getName());
        helper.setText(R.id.tvPrice,"若不是你突然闯进我生活、我怎会把死守的寂寞放任了、如果我真的没那么爱过、爱着一个没有灵魂的人、世界都是黑色");
        helper.setText(R.id.tvReason, TextUtils.isEmpty(item.getTitle()) ? "" : item.getTitle());
//        helper.setText(R.id.tvText, TextUtils.isEmpty(item.getText()) ? "" : item.getText());

    }
}
