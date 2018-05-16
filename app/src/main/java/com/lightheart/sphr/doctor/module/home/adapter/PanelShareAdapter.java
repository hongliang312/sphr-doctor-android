package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PanelShareModel;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by fucp on 2018-5-15.
 * Description :
 */

public class PanelShareAdapter extends BaseQuickAdapter<PanelShareModel, BaseViewHolder> {

    @Inject
    public PanelShareAdapter() {
        super(R.layout.item_panel_share, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PanelShareModel item) {
        helper.setText(R.id.tvPanelName, TextUtils.isEmpty(item.getShareTitle()) ? "" : item.getShareTitle());
        helper.setText(R.id.tvName, TextUtils.isEmpty(item.getSharerName()) ? "" : item.getSharerName());
        helper.setText(R.id.tvTime, item.getCreateTime() == 0 ? "" : " —— " + TimeUtils.millis2String(item.getCreateTime(), new SimpleDateFormat("yyyy/MM/dd")));
    }

}
