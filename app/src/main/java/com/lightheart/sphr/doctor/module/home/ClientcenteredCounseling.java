package com.lightheart.sphr.doctor.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.UntreatedBean;
import com.lightheart.sphr.doctor.module.home.activity.TelephoneDetailsActivity;
import com.lightheart.sphr.doctor.module.home.adapter.ConsulTationAdapter;
import com.lightheart.sphr.doctor.module.home.contract.UntreatedContract;
import com.lightheart.sphr.doctor.module.home.presenter.UntreatedPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 知足 on 2018/5/14.
 */

public class ClientcenteredCounseling extends BaseFragment<UntreatedPresenter> implements UntreatedContract.View,View.OnClickListener{


    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<UntreatedBean> content;
    private ConsulTationAdapter consulTationAdapter;
    private List<UntreatedBean> list = new ArrayList<>();
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
        assert mPresenter != null;
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        if ("待处理".equals(name)){

            type="SER_CST_S_ING";

        }else {

            type="SER_CST_S_END";

        }
        mPresenter.loadUntreatedData(type);

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void setUntreated(final List<UntreatedBean> content) {
        list.clear();
        list.addAll(content);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        consulTationAdapter=new ConsulTationAdapter(getContext(),list);
        recycler.setAdapter(consulTationAdapter);
        consulTationAdapter.listener(new ConsulTationAdapter.OnClicklistener() {
            @Override
            public void Oclick(View view, int position) {
                Intent intent = new Intent(getActivity(), TelephoneDetailsActivity.class);
                intent.putExtra("id",list.get(position).getConsultId()+"");
                intent.putExtra("type",type);
                getActivity().startActivity(intent);

                Log.i("bbbb",""+list.get(position).getConsultId());
            }
        });
    }


}
