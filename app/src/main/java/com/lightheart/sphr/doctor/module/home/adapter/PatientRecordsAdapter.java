package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 知足 on 2018/5/16.
 */

public class PatientRecordsAdapter extends BaseAdapter {

    private Context context;
    private List<PatientRecordsBean.CaseHistoriesBean> list;

    public PatientRecordsAdapter(Context context, List<PatientRecordsBean.CaseHistoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.patientrecords_item,null);
            holder.organizingtime = (TextView) convertView.findViewById(R.id.organizingtime);
            holder.clinictime =(TextView) convertView.findViewById(R.id.clinictime);
            holder.complaint= (TextView)convertView.findViewById(R.id.complaint);
            holder.finaldiagnosis =(TextView) convertView.findViewById(R.id.finaldiagnosis);
            holder.treatmentprocess =(TextView) convertView.findViewById(R.id.treatmentprocess);
            holder.therapeuticregimen =(TextView) convertView.findViewById(R.id.therapeuticregimen);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = TimeUtils.millis2String(list.get(position).getCreateTime(), new SimpleDateFormat("yyyy-MM-dd"));
        holder.organizingtime.setText(s);

  /*      String time = TimeUtils.millis2String(list.get(position).getCaseHistories().get(position).getCreateTime(), new SimpleDateFormat("yyyy-MM-dd"));
        holder.clinictime.setText(time);
        holder.complaint.setText(list.get(position).getCaseHistories().get(position).getChiefComplaint());
        holder.finaldiagnosis.setText(list.get(position).getCaseHistories().get(position).getDiagnosis());
        holder.treatmentprocess.setText(list.get(position).getCaseHistories().get(position).getTreatment());
        holder.therapeuticregimen.setText(list.get(position).getCaseHistories().get(position).getDrugs());*/

        String time = TimeUtils.millis2String(list.get(position).getCreateTime(), new SimpleDateFormat("yyyy-MM-dd"));
        holder.clinictime.setText(time);
        holder.complaint.setText(list.get(position).getChiefComplaint());
        holder.finaldiagnosis.setText(list.get(position).getDiagnosis());
        holder.treatmentprocess.setText(list.get(position).getTreatment());
        holder.therapeuticregimen.setText(list.get(position).getDrugs());

        return convertView;

    }
    public static class ViewHolder{

        TextView organizingtime;
        TextView clinictime;
        TextView complaint;
        TextView finaldiagnosis;
        TextView treatmentprocess;
        TextView therapeuticregimen;

    }
}
