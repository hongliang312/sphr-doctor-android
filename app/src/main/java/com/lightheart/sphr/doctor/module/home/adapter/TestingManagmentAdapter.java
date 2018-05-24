package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TestingManagement;

import java.text.SimpleDateFormat;

import javax.inject.Inject;
/**
 * Created by 知足 on 2018/5/18.
 */
public class TestingManagmentAdapter extends BaseQuickAdapter<TestingManagement,BaseViewHolder>{


    @Inject
    public TestingManagmentAdapter() {
        super(R.layout.testing_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestingManagement item) {

        helper.setText(R.id.guan,TextUtils.isEmpty(item.getProjectName()) ? "" :item.getProjectName());
        helper.setText(R.id.diduntil,TextUtils.isEmpty(item.getBidUnit()) ? "" :item.getBidUnit());
        helper.setText(R.id.test,TextUtils.isEmpty(item.getTrialStage()) ? "" :item.getTrialStage());
        helper.setText(R.id.recruit,item.getRecruitCount() == 0 ? "0" :item.getRecruitCount()+ "人");
    }


}
