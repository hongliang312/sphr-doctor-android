package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PatientsModel;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-14.
 * Description :
 */

public class PatientsAdapter extends BaseQuickAdapter<PatientsModel.PatientModel, BaseViewHolder> {

    @Inject
    public PatientsAdapter() {
        super(R.layout.patient_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientsModel.PatientModel item) {
        CircleImageView civPatient = helper.getView(R.id.civPatient);
        ImageLoaderUtils.display(mContext, civPatient, item.portrait, R.drawable.bg_grey, R.drawable.bg_grey);
        helper.setText(R.id.tvName, TextUtils.isEmpty(item.patientName) ? "" : item.patientName);
        ImageView ivSex = helper.getView(R.id.ivSex);
        if (TextUtils.equals("M", item.sex)) {
            ImageLoaderUtils.display(mContext, ivSex, R.mipmap.ic_male, R.drawable.bg_grey, R.drawable.bg_grey);
        } else if (TextUtils.equals("F", item.sex)) {
            ImageLoaderUtils.display(mContext, ivSex, R.mipmap.ic_female, R.drawable.bg_grey, R.drawable.bg_grey);
        } else {
            ImageLoaderUtils.display(mContext, ivSex, 0, R.drawable.bg_grey, R.drawable.bg_grey);
        }
        helper.setText(R.id.tvAge, TextUtils.isEmpty(item.birth) ? "" : item.birth);
        helper.setText(R.id.tvDease, TextUtils.isEmpty(item.disdesc) ? "" : item.disdesc);
    }
}
