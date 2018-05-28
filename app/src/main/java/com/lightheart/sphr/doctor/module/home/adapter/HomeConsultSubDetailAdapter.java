package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import java.util.List;

public class HomeConsultSubDetailAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context content;
    private List<HomeConsultSubDetail.ImgsBean> contentt;
    public HomeConsultSubDetailAdapter(Context content, List<HomeConsultSubDetail.ImgsBean> contentt) {
        this.content = content;
        this.contentt = contentt;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(content).inflate(R.layout.online_details_loader_item,parent,false);
        return new SubDetail(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ImageLoaderUtils.display(content,((SubDetail) holder).img,contentt.get(position).getMediaUrl());

    }

    @Override
    public int getItemCount() {
        return contentt !=null?contentt.size():0;
    }

    public static class SubDetail extends RecyclerView.ViewHolder{

        ImageView img;

        SubDetail(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }

}
