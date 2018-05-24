package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.TestDetails;
import java.math.BigDecimal;
import javax.inject.Inject;

/**
 * Created by 知足 on 2018/5/23.
 */


public class TestDetailsAdapter extends BaseQuickAdapter<TestDetails.CtrSiteAssignmentsBean,BaseViewHolder> {


    @Inject
    public TestDetailsAdapter(){
        super(R.layout.testdetails, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestDetails.CtrSiteAssignmentsBean item) {

        helper.setText(R.id.siteName, TextUtils.isEmpty(item.getSiteName()) ? "" :item.getSiteName());
        helper.setText(R.id.contacts,TextUtils.isEmpty(item.getPiName()) ? "" :item.getPiName());
        helper.setText(R.id.reseach, TextUtils.isEmpty(item.getResearcher())? "":item.getResearcher());
        helper.setText(R.id.plannedNum,item.getPlannedNum() == 0 ? "" :item.getPlannedNum()+"");
        helper.setText(R.id.involvedNum,item.getInvolvedNum() == 0 ? "" :item.getInvolvedNum()+"");
        float div = (float) div(item.getInvolvedNum(), item.getPlannedNum(), 1)*100;
        helper.setText(R.id.Thegrouprate,TextUtils.isEmpty(item.getContacts()) ? "" :div+"%");
        helper.setText(R.id.State,TextUtils.isEmpty(item.getProjectStatusName()) ? "" :item.getProjectStatusName());
        helper.setText(R.id.isStart,TextUtils.isEmpty(item.getIsStart()) ? "" :item.getIsStart());
        helper.setText(R.id.exitedNum,item.getExitedNum() == 0 ? "" :item.getExitedNum()+"");

    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
