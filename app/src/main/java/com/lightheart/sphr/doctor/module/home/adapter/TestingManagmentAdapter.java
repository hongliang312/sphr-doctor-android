package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TestingManagement;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 知足 on 2018/5/18.
 */

public class TestingManagmentAdapter extends BaseAdapter{

    private Context context;
    private List<TestingManagement> contentt;
    private OnClicklistener onClicklistener;

    @Inject
    public TestingManagmentAdapter(Context context, List<TestingManagement> contentt) {
        this.context = context;
        this.contentt = contentt;
    }

    @Override
    public int getCount() {
        return contentt!=null?contentt.size():0;
    }

    @Override
    public Object getItem(int position) {
        return contentt.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.testing_item,null);
            holder.guan =(TextView) convertView.findViewById(R.id.guan);
            holder.deatil =(ImageView) convertView.findViewById(R.id.deatil);
            holder.diduntil =(TextView) convertView.findViewById(R.id.diduntil);
            holder.recruit =(TextView) convertView.findViewById(R.id.recruit);
            holder.test =(TextView) convertView.findViewById(R.id.test);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            holder.guan.setText(contentt.get(position).getProjectName());
            holder.diduntil.setText(contentt.get(position).getBidUnit());
            holder.test.setText(contentt.get(position).getTrialStage());
            holder.recruit.setText(contentt.get(position).getRecruitCount()+"");
            holder.deatil.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onClicklistener.Oclick(holder.itemView,position);
               }
           });

        }

        return convertView;

    }

    public static class ViewHolder {

        public TextView guan;
        public ImageView deatil;
        public TextView diduntil;
        public TextView recruit;
        public TextView test;
        public View itemView;
    }
    public void listener(OnClicklistener onClicklistener){
        this.onClicklistener=onClicklistener;

    }
    public interface OnClicklistener{
        void Oclick(View view,int position);
    }
}
