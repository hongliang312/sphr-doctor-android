package com.lightheart.sphr.doctor.module.home.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeConsultSubDetailAdapter extends BaseQuickAdapter<HomeConsultSubDetail.ImgsBean,BaseViewHolder>{


    private List<String> imgsList= new ArrayList<>();;
    private ImageView tvImg;

    @Inject
    HomeConsultSubDetailAdapter() {
        super(R.layout.online_details_loader_item, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomeConsultSubDetail.ImgsBean item) {
        tvImg = helper.getView(R.id.tvImg);
        ImageLoaderUtils.display(mContext, tvImg, item.getMediaUrl());
        Log.d("fffff",""+item.getMediaUrl());
        imgsList.add(item.getMediaUrl());
        Log.i("hhhhh",""+imgsList.size());

        tvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    PictureConfig config = new PictureConfig.Builder()
                            .setListData((ArrayList<String>) imgsList)	//图片数据List<String> list
                            .setPosition(0)	//图片下标（从第position张图片开始浏览）
                            .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                            .setIsShowNumber(true)//是否显示数字下标
                            .needDownload(true)	//是否支持图片下载
                            .setPlacrHolder(R.mipmap.ic_launcher)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                            .build();
                    ImagePagerActivity.startActivity(mContext, config);
              }
        });

    }

}
