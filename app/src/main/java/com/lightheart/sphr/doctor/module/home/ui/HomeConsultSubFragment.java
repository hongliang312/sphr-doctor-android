package com.lightheart.sphr.doctor.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.ConsultingListBean;
import com.lightheart.sphr.doctor.module.home.adapter.ConsulTationAdapter;
import com.lightheart.sphr.doctor.module.home.contract.ConsultingListContract;
import com.lightheart.sphr.doctor.module.home.presenter.ConsultingListPresenter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
/**
 *
 * 在线咨询和电话咨询列表页面
 */

public class HomeConsultSubFragment extends BaseFragment<ConsultingListPresenter> implements ConsultingListContract.View{

    @BindView(R.id.tvRecycler)
    RecyclerView tvRecycler;
    private List<ConsultingListBean> content;
    private List<ConsultingListBean> list = new ArrayList<>();
    private String type="";

    @Override
    protected int getLayoutId() {
        return R.layout.clientcenteredcounseling;
    }
    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }
    @Override
    protected void initView(View view) {

        Bundle arguments = getArguments();
        assert arguments != null;
        String name = arguments.getString("name");
        String names = arguments.getString("names");

        if ("待处理".equals(name)){
            type="SER_CST_S_ING";
        }else {
            type="SER_CST_S_END";
        }
        getDataInternet(names);

    }

    private void getDataInternet(String names) {
        if("电话咨询".equals(names)){


        }else {
            assert mPresenter != null;
            mPresenter.loadConsultingListData(type);
        }
    }

    @Override
    public void setConsultingListData(final List<ConsultingListBean> content) {
        list.clear();
        list.addAll(content);
        tvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ConsulTationAdapter consulTationAdapter = new ConsulTationAdapter(getContext(), list);
        tvRecycler.setAdapter(consulTationAdapter);
        consulTationAdapter.listener(new ConsulTationAdapter.OnClicklistener() {
            @Override
            public void Oclick(View view, int position) {
                Intent intent = new Intent(getActivity(), HomeConsultSubDetailActivity.class);
                intent.putExtra("id",list.get(position).getConsultId()+"");
                intent.putExtra("type",type);
                getActivity().startActivity(intent);

            }
        });
    }


}
