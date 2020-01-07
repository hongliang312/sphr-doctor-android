package com.lightheart.sphr.doctor.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightheart.sphr.doctor.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin  2020/1/7/18:21
 * Describe
 * 作者 洪亮 admin
 */
public class FootListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mData;
    private LayoutInflater inflater;
    private List<Integer> heightList;//随机数集合

    public FootListAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.mData = data;
        inflater = LayoutInflater.from( mContext );
    }

    public void setData(List<String> data) {
        heightList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int height = new Random().nextInt(300) + 650;//随机数
            heightList.add(height);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new footItemHolder( inflater.inflate( R.layout.item_list, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      if(holder instanceof footItemHolder){
          footItemHolder itemHolder = (footItemHolder) holder;
          ViewGroup.LayoutParams layoutParams=itemHolder.layout.getLayoutParams();
          layoutParams.height=heightList.get(position);
          itemHolder.layout.setLayoutParams(layoutParams);
      }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class footItemHolder extends RecyclerView.ViewHolder{

        private final CardView layout;

        footItemHolder(View itemView) {
            super( itemView );
            layout = itemView.findViewById( R.id.layout );
        }
    }
}
