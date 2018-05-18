package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PanelsModel;

import javax.inject.Inject;

/**
 * Created by fucp on 2018-4-19.
 * Description :首页临床试验招募adapter
 */

public class PanelListAdapter extends BaseQuickAdapter<PanelsModel, BaseViewHolder> {

    private String mFlag;

    @Inject
    public PanelListAdapter() {
        super(R.layout.item_panel, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PanelsModel item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_purple);
                break;
            case 1:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_blue);
                break;
            case 2:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_yellow);
                break;
        }
        if (!TextUtils.isEmpty(item.getDtmAroName()) && item.getDtmAroName().length() >= 2) {
            String tx = item.getDtmAroName().substring(0, 2);
            helper.setText(R.id.tvImage, tx);
        } else {
            helper.setText(R.id.tvImage, TextUtils.isEmpty(item.getDtmAroName()) ? "" : item.getDtmAroName());
        }
        helper.setText(R.id.tvPanelName, TextUtils.isEmpty(item.getDtmAroName()) ? "" : item.getDtmAroName());
        helper.setText(R.id.tvNum, item.getDoctorList().size() + "  加入");
        helper.setVisible(R.id.ivAddPanel, TextUtils.equals("N", mFlag));
    }

    public void initData(String flag) {
        this.mFlag = flag;
    }
}
