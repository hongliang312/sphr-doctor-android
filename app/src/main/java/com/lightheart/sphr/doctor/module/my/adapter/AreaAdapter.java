package com.lightheart.sphr.doctor.module.my.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.AreaModel;

import javax.inject.Inject;

/**
 * Created by fucp on 2018-5-22.
 * Description :
 */

public class AreaAdapter extends BaseQuickAdapter<AreaModel, BaseViewHolder> {

    @Inject
    public AreaAdapter() {
        super(R.layout.item_area, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaModel item) {
        if (item != null) {
            helper.setText(R.id.tvName, TextUtils.isEmpty(item.getName()) ? "" : item.getName());
        }
    }
}
