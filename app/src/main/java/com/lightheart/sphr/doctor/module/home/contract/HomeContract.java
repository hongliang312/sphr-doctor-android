package com.lightheart.sphr.doctor.module.home.contract;

import com.lightheart.sphr.doctor.app.LoadType;
import com.lightheart.sphr.doctor.base.BaseContract;
import com.lightheart.sphr.doctor.bean.HomePageInfo;

import java.util.List;

/**
 * Created by fucp on 2018-4-20.
 * Description :
 */

public interface HomeContract {

    interface View extends BaseContract.BaseView{

        void setHomeBanners(List<HomePageInfo.BannerListBean> banners);

        void setNotices(List<HomePageInfo.NoticeListBean> noticeList);

        void setClinicals(List<HomePageInfo.ClinicalTrialListBean> clinicalTrialObj, @LoadType.checker int loadType);


    }

    interface Presenter extends BaseContract.BasePresenter<View >{

        void loadHomeData();

        void refresh();

    }

}
