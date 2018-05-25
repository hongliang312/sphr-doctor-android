package com.lightheart.sphr.doctor.module.home.adapter;
import android.text.TextUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import java.text.SimpleDateFormat;
import javax.inject.Inject;

/**
 * Created by 知足 on 2018/5/21.
 */

public class PatientRecordAdapter extends BaseQuickAdapter<PatientRecordsBean.CaseHistoriesBean,BaseViewHolder>{

    @Inject
     public PatientRecordAdapter(){
        super(R.layout.patientrecords_item, null);
     }

    @Override
    protected void convert(BaseViewHolder helper, PatientRecordsBean.CaseHistoriesBean item) {

        String time= TimeUtils.millis2String((Long) item.getCreateTime(),new SimpleDateFormat("yyyy-MM-dd"));
        String timer= TimeUtils.millis2String((Long) item.getClinicTime(),new SimpleDateFormat("yyyy-MM-dd"));
      //   helper.setText(R.id.organizingtime,item.getCreateTime() == 0 ? "" :time +"");
         helper.setText(R.id.clinictime, item.getCreateTime() == 0? "" :timer +"");
         helper.setText(R.id.complaint,TextUtils.isEmpty(item.getChiefComplaint()) ? "" :item.getChiefComplaint());
         helper.setText(R.id.finaldiagnosis,TextUtils.isEmpty(item.getDiagnosis()) ? "" :item.getDiagnosis());
         helper.setText(R.id.treatmentprocess,TextUtils.isEmpty(item.getTreatment()) ? "" :item.getTreatment());
         helper.setText(R.id.therapeuticregimen,TextUtils.isEmpty(item.getDrugs()) ? "" :item.getDrugs());

    }

}
