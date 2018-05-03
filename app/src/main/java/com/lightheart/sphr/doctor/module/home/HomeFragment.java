package com.lightheart.sphr.doctor.module.home;

import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.HomeMoudleManage;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.module.home.adapter.ClinicalAdapter;
import com.lightheart.sphr.doctor.module.home.adapter.HomeMoudleManagerAdapter;
import com.lightheart.sphr.doctor.module.home.contract.HomeContract;
import com.lightheart.sphr.doctor.module.home.presenter.HomePresenter;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fucp on 2018-4-19.
 * Description :首页
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rvHomeArticles)
    RecyclerView mRvHomeArticles;
    @Inject
    ClinicalAdapter mClinicalAdapter;
    private Banner mBannerAds;
    private List<HomeMoudleManage> moudleManageList = new ArrayList<>();
    private TextView mTvNotice;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        //  设置RecyclerView
        mRvHomeArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHomeArticles.setAdapter(mClinicalAdapter);

        // 设置BannerHeadView
        View mHomeBannerHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_banner_head, null);
        mBannerAds = mHomeBannerHeadView.findViewById(R.id.banner_ads);
        mTvNotice = mHomeBannerHeadView.findViewById(R.id.tvNotice);
        TextView mTvClinicalMore = mHomeBannerHeadView.findViewById(R.id.tvClinicalMore);
        mTvClinicalMore.setOnClickListener(this);

        // 设置管理模块
        RecyclerView rvGridTest = mHomeBannerHeadView.findViewById(R.id.rvGridTest);
        rvGridTest.setLayoutManager(new GridLayoutManager(getContext(), 4));
        String[] titles = getResources().getStringArray(R.array.home_moudle_title);
        String[] types = getResources().getStringArray(R.array.home_moudle_type);
        TypedArray images = getResources().obtainTypedArray(R.array.home_moudle_image);
        for (int i = 0; i < titles.length; i++) {
            HomeMoudleManage homeMoudleManage = new HomeMoudleManage();
            homeMoudleManage.type = types[i];
            homeMoudleManage.title = titles[i];
            homeMoudleManage.imgUrl = images.getResourceId(i, 1);
            moudleManageList.add(homeMoudleManage);
        }
        images.recycle();
        HomeMoudleManagerAdapter moudleManagerAdapter = new HomeMoudleManagerAdapter(R.layout.grid_home_moudle_manage, moudleManageList);
        moudleManagerAdapter.setOnItemClickListener(this);
        rvGridTest.setAdapter(moudleManagerAdapter);

        mClinicalAdapter.addHeaderView(mHomeBannerHeadView);
        mClinicalAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        assert mPresenter != null;
        mPresenter.loadHomeData();
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setHomeBanners(List<HomePageInfo.BannerListBean> banners) {
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        /*String[] stringArray = getResources().getStringArray(R.array.banner_title);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.banner_image);
        for (int i = 0; i < stringArray.length; i++) {
            images.add(typedArray.getResourceId(i, 1));
            titles.add(stringArray[i]);
        }
        typedArray.recycle();*/
        for (HomePageInfo.BannerListBean item : banners) {
            images.add(item.getImgUrl());
            titles.add(item.getTitle());
        }
        mBannerAds.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new ImageLoaderUtils())
                .start();
    }

    @Override
    public void setNotices(List<HomePageInfo.NoticeListBean> noticeList) {
        if (noticeList != null && noticeList.size() > 0)
            mTvNotice.setText(noticeList.get(0).getTitle());
    }

    @Override
    public void setClinicals(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialObj, int loadType) {
        setLoadDataResult(mClinicalAdapter, mSwipeRefreshLayout, clinicalTrialObj, loadType);
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }

    @Override
    public void onClick(View view) {
        ToastUtils.showShort(R.string.more);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof ClinicalAdapter) {
            HomePageInfo.ClinicalTrialListBean item = ((ClinicalAdapter) adapter).getItem(position);
            assert item != null;
            ToastUtils.showShort(item.getProjectName());
        } else {
            HomeMoudleManage item = (HomeMoudleManage) adapter.getItem(position);
            assert item != null;
            ToastUtils.showShort(item.title);
            switch (item.type) {
                case "PTM":

                    break;
                case "CLM":

                    break;
                case "PANEL":

                    break;
                case "COL":

                    break;
            }
        }
    }

}
