
package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;
public class PatientRecordsAdapter extends BaseQuickAdapter<PatientRecordsBean.CaseHistoriesBean, BaseViewHolder> {

    @Inject
    PatientRecordsAdapter() {
        super(R.layout.patientrecords_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientRecordsBean.CaseHistoriesBean item) {

        helper.setText(R.id.tvSetTime, TimeUtils.millis2String(item.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)));
        helper.setText(R.id.tvClinicTime, TimeUtils.millis2String(item.getClinicTime(), new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)));
        helper.setText(R.id.tvComplaint, TextUtils.isEmpty(item.getChiefComplaint()) ? "" : item.getChiefComplaint());
        helper.setText(R.id.tvFinalDiagnosis, TextUtils.isEmpty(item.getDiagnosis()) ? "" : item.getDiagnosis());
        helper.setText(R.id.tvTreatment, TextUtils.isEmpty(item.getTreatment()) ? "" : item.getTreatment());
        helper.setText(R.id.tvDosageSchedule, TextUtils.isEmpty(item.getDrugs()) ? "" : item.getDrugs());
    }

}