package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.UntreatedBean;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 知足 on 2018/5/15.
 */

public class ConsulTationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<UntreatedBean> untreatedlist;
    private OnClicklistener onClicklistener;

    @Inject
    public ConsulTationAdapter(Context context, List<UntreatedBean> untreatedlist) {
        this.context = context;
        this.untreatedlist = untreatedlist;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater.from(context).inflate(R.layout.pending_item,parent,false);
        Untreated untreated=new Untreated(inflater);
        return untreated;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        Untreated  untreated= (Untreated) holder;

        Date date=new Date(untreatedlist.get(position).getConsultDate());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String strs=sdf.format(date);
        untreated.time.setText(strs);
        untreated.name.setText(untreatedlist.get(position).getName());
        untreated.condition.setText(untreatedlist.get(position).getContent());
        untreated.lookover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicklistener.Oclick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return untreatedlist!=null?untreatedlist.size():0;
    }

    public static class Untreated extends RecyclerView.ViewHolder{

        public TextView time;
        public TextView name;
        public TextView condition;
        public Button lookover;

        public Untreated(View itemView) {

            super(itemView);
            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            condition = itemView.findViewById(R.id.condition);
            lookover = itemView.findViewById(R.id.lookover);
        }
    }
    public void listener(OnClicklistener onClicklistener){
        this.onClicklistener=onClicklistener;

    }
    public interface OnClicklistener{
        void Oclick(View view,int position);
    }
}
