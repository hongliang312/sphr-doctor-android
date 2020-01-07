package com.lightheart.sphr.doctor.module.home;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.HomeModuleManage;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.module.home.adapter.ClinicalAdapter;
import com.lightheart.sphr.doctor.module.home.adapter.HomeModuleManagerAdapter;
import com.lightheart.sphr.doctor.module.home.contract.HomeContract;
import com.lightheart.sphr.doctor.module.home.presenter.HomePresenter;
import com.lightheart.sphr.doctor.module.home.ui.ClinicalRecruitDetailActivity;
import com.lightheart.sphr.doctor.module.home.ui.ClinicalTrailManageActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomeClinicalRecruitActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomeConsultActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomeNoticeActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomePanelActivity;
import com.lightheart.sphr.doctor.module.home.ui.HomePatientManageActivity;
import com.lightheart.sphr.doctor.module.home.ui.Main2Activity;
import com.lightheart.sphr.doctor.module.home.ui.Main3Activity;
import com.lightheart.sphr.doctor.module.main.ui.MainActivity;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;
import com.lightheart.sphr.doctor.view.VerticalTextView;
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
    private List<HomeModuleManage> moduleManageList = new ArrayList<>();
    private VerticalTextView mTvNotice;
    private boolean isFirstEnter = true;

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
        // 设置RecyclerView
        mRvHomeArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHomeArticles.setAdapter(mClinicalAdapter);

        // 设置BannerHeadView
        View mHomeBannerHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_banner_head, null);
        mBannerAds = mHomeBannerHeadView.findViewById(R.id.banner_ads);
        mTvNotice = mHomeBannerHeadView.findViewById(R.id.tvNotice);
        LinearLayout llTelConsult = mHomeBannerHeadView.findViewById(R.id.llTelConsult);
        LinearLayout llOnlineConsult = mHomeBannerHeadView.findViewById(R.id.llOnlineConsult);
        TextView mTvClinicalMore = mHomeBannerHeadView.findViewById(R.id.tvClinicalMore);
        llTelConsult.setOnClickListener(this);
        llOnlineConsult.setOnClickListener(this);
        mTvClinicalMore.setOnClickListener(this);

        //设置管理模块
        RecyclerView rvGridTest = mHomeBannerHeadView.findViewById(R.id.rvGridTest);
        rvGridTest.setLayoutManager(new GridLayoutManager(getContext(), 3));
        String[] titles = getResources().getStringArray(R.array.home_moudle_title);
        String[] types = getResources().getStringArray(R.array.home_moudle_type);
        TypedArray images = getResources().obtainTypedArray(R.array.home_moudle_image);
        for (int i = 0; i < titles.length; i++) {
            HomeModuleManage homeModuleManage = new HomeModuleManage();
            homeModuleManage.type = types[i];
            homeModuleManage.title = titles[i];
            homeModuleManage.imgUrl = images.getResourceId(i, 1);
            moduleManageList.add(homeModuleManage);
        }
        images.recycle();
        HomeModuleManagerAdapter moduleManagerAdapter = new HomeModuleManagerAdapter(R.layout.grid_home_moudle_manage, moduleManageList);
        moduleManagerAdapter.setOnItemClickListener(this);
        rvGridTest.setAdapter(moduleManagerAdapter);

        mClinicalAdapter.addHeaderView(mHomeBannerHeadView);
        mClinicalAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);

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
    public void setNotices(final List<HomePageInfo.NoticeListBean> noticeList) {
        if (noticeList != null && noticeList.size() > 0) {
            List<String> titleList = new ArrayList<>();
            for (int i = 0; i < noticeList.size(); i++) {
                String title = noticeList.get(i).getTitle();
                titleList.add(title);
            }
            mTvNotice.setTextList((ArrayList<String>) titleList);

            mTvNotice.setText(16, 0, R.color.theme_color);
            mTvNotice.setTextStillTime(4000);//设置停留时长间隔
            mTvNotice.setAnimTime(600);//设置进入和退出的时间间隔
            mTvNotice.startAutoScroll();
            mTvNotice.setOnItemClickListener(new VerticalTextView.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    startActivity(new Intent(getActivity(), HomeNoticeActivity.class)
                            .putExtra("title", noticeList.get(position).getTitle())
                            .putExtra("content", noticeList.get(position).getContent()));
                }
            });
        }
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstEnter) mTvNotice.startAutoScroll();
        isFirstEnter = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        mTvNotice.stopAutoScroll();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTvNotice.stopAutoScroll();
    }

    @Override
    public void setClinicals(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialObj, int loadType) {
        if (clinicalTrialObj != null && clinicalTrialObj.size() > 0)
            setLoadDataResult(mClinicalAdapter, mSwipeRefreshLayout, clinicalTrialObj, loadType);
        else initEmptyView(mClinicalAdapter, mRvHomeArticles);
    }

    @Override
    public void onRefresh() {
        assert mPresenter != null;
        mPresenter.refresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llTelConsult:
                startActivity(new Intent(getActivity(), HomeConsultActivity.class).putExtra("consult_type", "TEL"));
                break;
            case R.id.llOnlineConsult:
                startActivity(new Intent(getActivity(), HomeConsultActivity.class).putExtra("consult_type", "ONLINE"));
                break;
            case R.id.tvClinicalMore:
                startActivity(new Intent(getActivity(), HomeClinicalRecruitActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof ClinicalAdapter) {
            HomePageInfo.ClinicalTrialListBean item = ((ClinicalAdapter) adapter).getItem(position);
            assert item != null;
            startActivity(new Intent(getActivity(), ClinicalRecruitDetailActivity.class).putExtra("id", item.getId()));
        } else {
            HomeModuleManage item = (HomeModuleManage) adapter.getItem(position);
            assert item != null;
            switch (item.type) {
                case "PTM":
//                    startActivity(new Intent(getActivity(), HomePatientManageActivity.class));
                    startActivity(new Intent(getActivity(), Main3Activity.class));

                    break;
                case "CLM":
//                    startActivity(new Intent(getActivity(), ClinicalTrailManageActivity.class));
                    startActivity(new Intent(getActivity(), Main2Activity.class));
                    break;
                case "PANEL":
                    startActivity(new Intent(getActivity(), HomePanelActivity.class));
                    break;
            }
        }
    }

}
