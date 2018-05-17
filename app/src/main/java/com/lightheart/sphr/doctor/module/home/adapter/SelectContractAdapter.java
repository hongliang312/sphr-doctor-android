package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-5-16.
 * Description :
 */

public class SelectContractAdapter extends BaseQuickAdapter<DoctorBean, BaseViewHolder> {

    // 用来控制CheckBox的选中状况,防止出现复用item时错乱
    private Map<Integer, Boolean> isSelectedMap = new HashMap<>();

    @Inject
    public SelectContractAdapter() {
        super(R.layout.item_contract_select, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorBean item) {
        CheckBox cbContract = helper.getView(R.id.cbContract);
        CircleImageView civContract = helper.getView(R.id.civContract);
        TextView tvConName = helper.getView(R.id.tvConName);
        ImageLoaderUtils.display(mContext, civContract, item.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
        tvConName.setText(TextUtils.isEmpty(item.getContName()) ? "" : item.getContName());
        cbContract.setChecked(getIsSelected().get(helper.getLayoutPosition()));
    }

    public void initData(List<DoctorBean> contractDocList) {
        if (contractDocList != null && contractDocList.size() > 0) {
            for (int i = 0; i < contractDocList.size(); i++) {
                if (isSelectedMap.get(i) == null) {// 加载更多的时候避免把之前选择项置为false
                    isSelectedMap.put(i, contractDocList.get(i).isCheck());
                }
            }
        }
    }

    // 获取条目是否选中的状态
    public Map<Integer, Boolean> getIsSelected() {
        return isSelectedMap;
    }

}
