package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TelephoneDetailsBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import java.util.List;

/**
 * Created by 知足 on 2018/5/16.
 */

public class TelephoneDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context content;
    private List<TelephoneDetailsBean.ImgsBean> contentt;
    public TelephoneDetailsAdapter(Context content, List<TelephoneDetailsBean.ImgsBean> contentt) {
        this.content = content;
        this.contentt = contentt;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(content).inflate(R.layout.telephonedetails,parent,false);
        Telephone telephone=new Telephone(inflater);
        return telephone;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Telephone telephonebean= (Telephone) holder;
        ImageLoaderUtils.display(content,((Telephone) holder).img,contentt.get(position).getMediaUrl());

    }

    @Override
    public int getItemCount() {
        return contentt !=null?contentt.size():0;
    }

    public static class Telephone extends RecyclerView.ViewHolder{

        public ImageView img;

        public Telephone(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }

}
