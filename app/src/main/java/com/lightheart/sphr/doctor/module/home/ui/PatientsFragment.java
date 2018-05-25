package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.PatientsModel;
import com.lightheart.sphr.doctor.module.home.adapter.PatientsAdapter;
import com.lightheart.sphr.doctor.module.home.contract.PatientManageContract;
import com.lightheart.sphr.doctor.module.home.presenter.PatientsPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-5-24.
 * Description : 患者列表
 */

public class PatientsFragment extends BaseFragment<PatientsPresenter> implements PatientManageContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvPatients)
    RecyclerView mRvPatients;
    @Inject
    PatientsAdapter mPatientsAdapter;
    private int mPage;
    private int timeCategory;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patient;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        timeCategory = getArguments().getInt("timeCategory", 1);
        mPage = getArguments().getInt("mPage", 1);

        //  设置RecyclerView
        mRvPatients.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPatients.setAdapter(mPatientsAdapter);

        mPatientsAdapter.setOnItemClickListener(this);
//        mPatientsAdapter.setOnLoadMoreListener(this, mRvPatients); // 目前接口不支持
        swipeRefreshLayout.setEnabled(false);

        assert mPresenter != null;
        mPresenter.loadPatientData(mPage, timeCategory);
    }

    public static PatientsFragment newInstance(int itimeCategory, int mPage) {
        PatientsFragment fragment = new PatientsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("timeCategory", itimeCategory);
        bundle.putInt("mPage", mPage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPatients(List<PatientsModel.PatientModel> patientModels, int loadType) {
        setLoadDataResult(mPatientsAdapter, swipeRefreshLayout, patientModels, loadType);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PatientsModel.PatientModel item = (PatientsModel.PatientModel) adapter.getItem(position);
        assert item != null;
        startActivity(new Intent(getActivity(), PatientRecordsActivity.class).putExtra("id", item.duid));
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        assert mPresenter != null;
        mPresenter.loadMore(mPage, timeCategory);
    }
}
