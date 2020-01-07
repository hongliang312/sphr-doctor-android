package com.lightheart.sphr.doctor.module.home.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.bean.PanelSection;
import com.lightheart.sphr.doctor.bean.PanelsModel;

import java.util.List;

public class PanelSectionAdapter extends BaseSectionQuickAdapter<PanelSection, BaseViewHolder> {

    private SectionItemListener mSectionItemListener;

    public PanelSectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final PanelSection item) {
        helper.setText(R.id.tvTitle, item.header);
        TextView tvAll = helper.getView(R.id.tvAll);
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSectionItemListener != null) {
                    int adapterPosition = helper.getAdapterPosition();
                    mSectionItemListener.tvAllClick(view, adapterPosition, item);
                }
            }
        });
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PanelSection item) {
        PanelsModel panelsModel = item.t;
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_purple);
                break;
            case 1:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_blue);
                break;
            case 2:
                helper.setBackgroundRes(R.id.tvImage, R.drawable.bg_yellow);
                break;
        }
        helper.setText(R.id.tvImage, TextUtils.isEmpty(panelsModel.getDtmAroName()) ? "" : panelsModel.getDtmAroName().substring(0, 2));
        helper.setText(R.id.tvPanelName, TextUtils.isEmpty(panelsModel.getDtmAroName()) ? "" : panelsModel.getDtmAroName());
        if (panelsModel.getDoctorList() != null){
            helper.setText(R.id.tvNum, panelsModel.getDoctorList().size() + "  加入");
        }
        helper.setVisible(R.id.ivAddPanel, !panelsModel.isAdded());
        LinearLayout llPanel = helper.getView(R.id.llPanel);
        llPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSectionItemListener != null) {
                    int adapterPosition = helper.getAdapterPosition();
                    mSectionItemListener.itemClick(view, adapterPosition, item);
                }
            }
        });
    }
    public void setOnSectionItemListener(PanelSectionAdapter.SectionItemListener listener) {
        mSectionItemListener = listener;
    }

    public interface SectionItemListener {

        void tvAllClick(View view, int position, PanelSection item);//全部标签点击

        void itemClick(View view, int position, PanelSection item); //点击item

    }

}
