package com.lightheart.sphr.doctor.module.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.HomeModuleManage;

import java.util.List;

/**
 * Created by fucp on 2018-4-24.
 * Description :首页管理模块adapter
 */

public class HomeModuleManagerAdapter extends BaseQuickAdapter<HomeModuleManage, BaseViewHolder> {

    public HomeModuleManagerAdapter(int grid_home_moudle_manage, List<HomeModuleManage> moudleManageList) {
        super(grid_home_moudle_manage, moudleManageList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModuleManage item) {
        helper.setText(R.id.tvHomeMoudle, item.title);
        helper.setImageResource(R.id.ivHomeMoudle, item.imgUrl);
    }

}
