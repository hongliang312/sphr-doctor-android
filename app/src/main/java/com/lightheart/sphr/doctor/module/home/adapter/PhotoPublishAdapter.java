package com.lightheart.sphr.doctor.module.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guwu.common.utils.DisplayUtil;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.List;

/**
 * created by dalang at 2018/11/26
 */
public class PhotoPublishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<String> mData;
    private int limit = 0;
    private LayoutInflater inflater;
    private int size_initial;//判断图片集合的初始数量 用于再次编辑又发布的情况
    public PhotoPublishAdapter(Context mContext, List<String> data, int limit) {
        this.mContext = mContext;
        this.mData = data;
        this.limit = limit;
        inflater = LayoutInflater.from( mContext );
    }

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder( inflater.inflate( R.layout.item_photo, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            int width = DisplayUtil.getScreenWidth((Activity) mContext);
            LinearLayout.LayoutParams prm = (LinearLayout.LayoutParams) itemHolder.mImageView.getLayoutParams();//获取按钮的布局
            prm.width = (int) (width - mContext.getResources().getDimension(R.dimen.dp_60)) / 3;
            prm.height = (int) (width - mContext.getResources().getDimension(R.dimen.dp_60)) / 3;
            itemHolder.mImageView.setLayoutParams(prm);
            if (position == mData.size()) {
                itemHolder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_add_photo));
                //大于limit则隐藏+号图片
                if (position > limit) {
                    itemHolder.mImageView.setVisibility(View.GONE);
                } else {
                    itemHolder.mImageView.setVisibility(View.VISIBLE);
                }
            } else {
                if (position < size_initial) {
                    ImageLoaderUtils.display( mContext,itemHolder.mImageView,mData.get( position ) );
                } else {
                    ImageLoaderUtils.display( mContext,itemHolder.mImageView,mData.get( position ) );
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData.size() == limit) {
            return limit;
        } else {
            return mData.size() + 1;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        ItemViewHolder(View itemView) {
            super( itemView );
            mImageView = itemView.findViewById( R.id.img_upload );

        }
    }


}
