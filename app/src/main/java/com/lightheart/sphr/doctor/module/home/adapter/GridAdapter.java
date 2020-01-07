package com.lightheart.sphr.doctor.module.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.GridBean;

/**
 * Created by admin  2020/1/7/17:57
 * Describe
 * 作者 洪亮 admin
 */
public class GridAdapter extends BaseQuickAdapter<GridBean, BaseViewHolder> {

    public GridAdapter(int ResId) {
        super( ResId );
    }

    @Override
    protected void convert(BaseViewHolder helper, GridBean item) {
        helper.setText( R.id.tv_name,item.getName());

    }
}
