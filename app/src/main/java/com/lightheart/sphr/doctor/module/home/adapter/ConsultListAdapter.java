package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.ConsultModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

public class ConsultListAdapter extends BaseQuickAdapter<ConsultModel, BaseViewHolder> {

    @Inject
    public ConsultListAdapter() {
        super(R.layout.item_consult, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultModel item) {
        helper.setText(R.id.tvTime, item.getConsultDate() == 0 ? "" : TimeUtils.millis2String(item.getConsultDate(), new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)));
        helper.setText(R.id.tvName, TextUtils.isEmpty(item.getName()) ? "" : item.getName());
        helper.setText(R.id.tvDes, TextUtils.isEmpty(item.getContent()) ? "" : item.getContent());
    }

}
