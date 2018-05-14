package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TestingManagement;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 知足 on 2018/5/9.
 */

public class TestingManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

     private Context context;
     private List<TestingManagement> contentt;
     private OnClicklistener onClicklistener;


    @Inject
    public TestingManagementAdapter(Context context, List<TestingManagement> contentt) {
        this.context = context;
        this.contentt = contentt;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.testing_item, parent, false);
        Testing text= new Testing(inflate);
        return text;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Testing text=(Testing)holder;

        ((Testing) holder).guan.setText(contentt.get(position).getProjectName());
        ((Testing) holder).diduntil.setText(contentt.get(position).getBidUnit());
        ((Testing) holder).recruit.setText(contentt.get(position).getRecruitCount()+"");
        ((Testing) holder).test.setText(contentt.get(position).getTrialStage());
        ((Testing) holder).deatil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicklistener.Oclick(holder.itemView,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentt!=null?contentt.size():0;
    }

        public static class Testing extends RecyclerView.ViewHolder{

            public TextView guan;
            public ImageView deatil;
            public TextView diduntil;
            public TextView recruit;
            public TextView test;
            public Testing(View itemView) {
                super(itemView);
                guan = itemView.findViewById(R.id.guan);
                deatil = itemView.findViewById(R.id.deatil);
                diduntil = itemView.findViewById(R.id.diduntil);
                recruit = itemView.findViewById(R.id.recruit);
                test = itemView.findViewById(R.id.test);
            }
        }
    public void listener(OnClicklistener onClicklistener){
        this.onClicklistener=onClicklistener;

    }
    public interface OnClicklistener{
        void Oclick(View view,int position);
    }
}
