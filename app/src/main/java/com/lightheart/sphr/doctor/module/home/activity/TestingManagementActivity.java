package com.lightheart.sphr.doctor.module.home.activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.TestingManagement;
import com.lightheart.sphr.doctor.module.home.adapter.TestingManagementAdapter;
import com.lightheart.sphr.doctor.module.home.contract.FabScrollListener;
import com.lightheart.sphr.doctor.module.home.contract.HideScrollListener;
import com.lightheart.sphr.doctor.module.home.contract.TestingManagementContract;
import com.lightheart.sphr.doctor.module.home.presenter.TestingManagementPresenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class TestingManagementActivity extends BaseActivity<TestingManagementPresenter> implements TestingManagementContract.View,HideScrollListener, View.OnClickListener {


    @BindView(R.id.Recycleview)
    RecyclerView Recycleview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TestingManagementAdapter testingAdapter;
    private List<TestingManagement> content = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testing;
    }


    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

        assert mPresenter != null;
        mPresenter.loadTestData();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setTesting(final List<TestingManagement> content) {
        int size = content.size();
        Log.i("zzz",""+size);
        Recycleview.setLayoutManager(new LinearLayoutManager(this));
        testingAdapter = new TestingManagementAdapter(this,content);
        Recycleview.setAdapter(testingAdapter);
        testingAdapter.listener(new TestingManagementAdapter.OnClicklistener() {
            @Override
            public void Oclick(View view, int position) {
                Intent intent = new Intent(TestingManagementActivity.this,TestDetailsActivity.class);
                intent.putExtra("id",content.get(position).getId()+"");
                startActivity(intent);

                Log.i("aaa",""+content.get(position).getId());

            }
        });
        Recycleview.addOnScrollListener(new FabScrollListener((HideScrollListener) this));

    }

    @Override
    public void onHide() {
        //隐藏动画
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void onShow() {
        // 显示动画--属性动画
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    }
}
