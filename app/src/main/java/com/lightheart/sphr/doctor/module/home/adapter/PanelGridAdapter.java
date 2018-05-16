package com.lightheart.sphr.doctor.module.home.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-15.
 * Description :
 */

public class PanelGridAdapter extends BaseQuickAdapter<DoctorBean, BaseViewHolder> {

    @Inject
    public PanelGridAdapter() {
        super(R.layout.grid_panel_member, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorBean item) {
        helper.setText(R.id.tvMemberName, TextUtils.isEmpty(item.getDoctorName()) ? "" : item.getDoctorName());
        CircleImageView view = helper.getView(R.id.civPanel);
        if (TextUtils.equals("添加成员", item.getDoctorName())) {
            ImageLoaderUtils.display(mContext, view, R.mipmap.ic_add_panel, R.drawable.bg_grey, R.drawable.bg_grey);
        } else {
            ImageLoaderUtils.display(mContext, view, item.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
        }
    }

}
