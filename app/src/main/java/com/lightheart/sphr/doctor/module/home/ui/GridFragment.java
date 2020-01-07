package com.lightheart.sphr.doctor.module.home.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.GridBean;
import com.lightheart.sphr.doctor.module.home.adapter.GridAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by admin  2020/1/7/17:53
 * Describe
 * 作者 洪亮 admin
 */
public class GridFragment extends BaseFragment {

    @BindView(R.id.grid_list)
    RecyclerView gridList;
    Unbinder unbinder;
    private List<GridBean> data;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__grid;
    }

    @Override
    protected void initInjector() {
    }

    /**
     * 传入需要的参数，dataList
     *
     * @param dataList
     * @return
     */
    public static GridFragment newInstance(List<GridBean> dataList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable( "list", (Serializable) dataList );
        GridFragment fragment = new GridFragment();
        fragment.setArguments( bundle );
        return fragment;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            data = (List<GridBean>) bundle.getSerializable( "list" );
        }
        GridAdapter gridAdapter = new GridAdapter( R.layout.item_home_grid );
        gridList.setAdapter( gridAdapter );
        gridAdapter.setNewData( data );

    }
}
