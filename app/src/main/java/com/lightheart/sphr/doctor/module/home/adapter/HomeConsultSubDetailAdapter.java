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

import static com.SuperKotlin.pictureviewer.PictureConfig.position;

public class HomeConsultSubDetailAdapter extends BaseQuickAdapter<HomeConsultSubDetail.ImgsBean,BaseViewHolder>{

    private OnClicklistener onClicklistener;

    @Inject
    HomeConsultSubDetailAdapter() {
        super(R.layout.online_details_loader_item, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomeConsultSubDetail.ImgsBean item) {
        ImageView tvImg = helper.getView(R.id.tvImg);
        ImageLoaderUtils.display(mContext, tvImg, item.getMediaUrl());
         tvImg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onClicklistener.onClick(helper.itemView,position);
             }
         });
        }

    public void listener(OnClicklistener onClicklistener){
        this.onClicklistener=onClicklistener;

    }
    public interface OnClicklistener{
        void onClick(View view,int position);
    }

}
