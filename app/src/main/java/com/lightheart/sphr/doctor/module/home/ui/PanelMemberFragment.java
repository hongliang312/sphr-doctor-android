package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.app.Constant;
import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.Apply2PanelParam;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.PanelShareModel;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PanelGridAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PanelShareContract;
import com.lightheart.sphr.doctor.module.home.presenter.PanelSharePresenter;
import com.lightheart.sphr.doctor.module.my.ui.MyHomePageActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-15.
 * Description : 专家组成员页面
 */

public class PanelMemberFragment extends BaseFragment<PanelSharePresenter> implements PanelShareContract.View, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

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
        mFragmentComponent.inject(this);
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
        } else {
            assert panelsModel != null;
            DoctorBean doctorDetail = new DoctorBean();
            doctorDetail.setDoctorName("添加成员");
            panelsModel.getDoctorList().add(doctorDetail);
        }

        mPanelGridAdapter.setOnItemClickListener(this);
        swipeRefreshLayout.setEnabled(false);

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
        DoctorBean item = (DoctorBean) adapter.getItem(position);
        assert item != null;
        if (position == panelsModel.getDoctorList().size() - 1) {
            if (position == 0) {
                startActivity(new Intent(getActivity(), SelectContactActivity.class).putExtra("flag", "INVITE"));
            }
        } else {
            startActivity(new Intent(getActivity(), MyHomePageActivity.class).putExtra("duid", item.getDuid()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCreate:
                Apply2PanelParam apply2PanelParam = new Apply2PanelParam();
                apply2PanelParam.duid = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
                apply2PanelParam.creator = panelsModel.getCreator();
                apply2PanelParam.doctorName = panelsModel.getDoctorName();
                apply2PanelParam.dtmAroId = panelsModel.getDtmAroId();
                apply2PanelParam.dtmAroName = panelsModel.getDtmAroName();
                assert mPresenter != null;
                mPresenter.apply2Panel(apply2PanelParam);
                break;
        }
    }

    @Override
    public void setPanelShare(List<PanelShareModel> panelsModels, int loadType) {
    }

    @Override
    public void success2ApplyPanel() {
        ToastUtils.showShort(getString(R.string.add_friend_hint));
        getActivity().finish();
    }
}
