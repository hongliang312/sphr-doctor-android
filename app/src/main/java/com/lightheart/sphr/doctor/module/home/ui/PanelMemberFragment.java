package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelGridAdapter;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description : 专家组成员页面
 */

public class PanelMemberFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvMember)
    RecyclerView rvMember;
    private PanelsModel panelsModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panel_member_share;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView(View view) {
        panelsModel = (PanelsModel) getArguments().getSerializable("detail");
        String mFlag = getArguments().getString("flag");

        PanelGridAdapter mPanelGridAdapter = new PanelGridAdapter();

        // 设置RecyclerView
        rvMember.setLayoutManager(new GridLayoutManager(getContext(), 5));
        rvMember.setAdapter(mPanelGridAdapter);
        // 设置header
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_panel, null);
        TextView tvImage = headerView.findViewById(R.id.tvImage);
        TextView tvPanelName = headerView.findViewById(R.id.tvPanelName);
        TextView tvNum = headerView.findViewById(R.id.tvNum);
        mPanelGridAdapter.addHeaderView(headerView);

        // 设置footer
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_panel, null);
        TextView tvCreate = footerView.findViewById(R.id.tvCreate);
        tvCreate.setText(getString(R.string.apply_add_panel));
        tvCreate.setOnClickListener(this);
        if (!TextUtils.equals("Y", mFlag)) {
            mPanelGridAdapter.addFooterView(footerView);
        }

        mPanelGridAdapter.setOnItemClickListener(this);
        swipeRefreshLayout.setEnabled(false);

        assert panelsModel != null;
        PanelsModel.DoctorDetail doctorDetail = new PanelsModel.DoctorDetail();
        doctorDetail.doctorName = "添加成员";
        panelsModel.getDoctorList().add(doctorDetail);
        if (!TextUtils.isEmpty(panelsModel.getDtmAroName()) && panelsModel.getDtmAroName().length() >= 2) {
            String tx = panelsModel.getDtmAroName().substring(0, 2);
            tvImage.setText(tx);
        } else {
            tvImage.setText(TextUtils.isEmpty(panelsModel.getDtmAroName()) ? " " : panelsModel.getDtmAroName());
        }
        tvPanelName.setText(TextUtils.isEmpty(panelsModel.getDtmAroName()) ? " " : panelsModel.getDtmAroName());
        tvNum.setText(panelsModel.getDoctorList().size() - 1 + "  加入");

        setLoadDataResult(mPanelGridAdapter, swipeRefreshLayout, panelsModel.getDoctorList(), LoadType.TYPE_REFRESH_SUCCESS);

    }

    public static PanelMemberFragment newIntance(PanelsModel mPanelsModel, String flag) {
        PanelMemberFragment fragment = new PanelMemberFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("detail", mPanelsModel);
        bundle.putSerializable("flag", flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PanelsModel.DoctorDetail item = (PanelsModel.DoctorDetail) adapter.getItem(position);
        assert item != null;
        if (position == panelsModel.getDoctorList().size() - 1) {
            ToastUtils.showShort(item.doctorName);
        } else {
            startActivity(new Intent(getActivity(), MyHomePageActivity.class).putExtra("duid", item.duid));
        }
    }

    @Override
    public void onClick(View view) {

    }
}
