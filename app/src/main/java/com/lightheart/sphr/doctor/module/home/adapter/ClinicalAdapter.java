package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.HomePageInfo;

import javax.inject.Inject;

/**
 * Created by fucp on 2018-4-19.
 * Description :首页临床试验招募adapter
 */

public class ClinicalAdapter extends BaseQuickAdapter<HomePageInfo.ClinicalTrialListBean, BaseViewHolder> {

    @Inject
    public ClinicalAdapter() {
        super(R.layout.item_clinical, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageInfo.ClinicalTrialListBean item) {

        helper.setText(R.id.tvClinicalTitle, TextUtils.isEmpty(item.getProjectName()) ? "" : item.getProjectName());
        helper.setText(R.id.tvRecNum, item.getRecruitCount() == 0 ? "" : item.getRecruitCount() + "");
        helper.setText(R.id.tvTrialStage, TextUtils.isEmpty(item.getTrialStage()) ? "" : item.getTrialStage());
        helper.setText(R.id.tvAdDis, TextUtils.isEmpty(item.getIndications()) ? "" : item.getIndications());
        helper.setText(R.id.tvBidingUnit, TextUtils.isEmpty(item.getBidUnit()) ? "" : item.getBidUnit());
    }

}
