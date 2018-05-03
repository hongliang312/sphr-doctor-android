package com.lightheart.sphr.doctor.module.contracts.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.ContractDocItem;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class ContractsAdapter extends BaseQuickAdapter<ContractDocItem, BaseViewHolder> {

    private Context mContext;

    //    @Inject
    public ContractsAdapter(Context context, int item_doc_contract) {
        super(item_doc_contract, null);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractDocItem item) {
        CircleImageView civContract = helper.getView(R.id.civContract);
        ImageLoaderUtils.display(mContext, civContract, item.getImgUrl(), R.drawable.bg_grey, R.drawable.bg_grey);
        helper.setText(R.id.tvConName, TextUtils.isEmpty(item.getContName()) ? "" : item.getContName());
    }
}
