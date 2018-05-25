package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.ClinicalTrailModel;

import javax.inject.Inject;

public class ClinicalTrailManageAdapter extends BaseQuickAdapter<ClinicalTrailModel, BaseViewHolder> {


    @Inject
    public ClinicalTrailManageAdapter() {
        super(R.layout.item_clinical_trail, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClinicalTrailModel item) {

        helper.setText(R.id.tvTitle, TextUtils.isEmpty(item.getProjectName()) ? "" : item.getProjectName());
        helper.setText(R.id.tvBindUnit, TextUtils.isEmpty(item.getBidUnit()) ? "" : item.getBidUnit());
        helper.setText(R.id.tvTrialStage, TextUtils.isEmpty(item.getTrialStage()) ? "" : item.getTrialStage());
        helper.setText(R.id.tvRecruitNum, item.getRecruitCount() == 0 ? "0" : item.getRecruitCount() + "人");
    }

}
