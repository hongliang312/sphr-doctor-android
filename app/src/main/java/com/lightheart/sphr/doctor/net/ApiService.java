package com.lightheart.sphr.doctor.net;

import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.FeedBackBean;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.RequestParams;
import com.lightheart.sphr.doctor.bean.TestDetails;
import com.lightheart.sphr.doctor.bean.TestingManagement;
import com.lightheart.sphr.doctor.bean.TextsingRequestParams;
import com.lightheart.sphr.doctor.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 登录
     *
     * @param parmas
     * @return User
     */
    @POST("user/login")
    Observable<DataResponse<User>> login(@Body LoginRequest parmas);

    /**
     * 验证码登录
     *
     * @param parmas
     * @return User
     */
    @POST("auth/code/login")
    Observable<DataResponse<User>> authCodeLogin(@Body LoginRequest parmas);

    /**
     * 发送验证码
     *
     * @param parmas
     * @return Object
     */
    @POST("auth/code/send")
    Observable<DataResponse<Object>> sendAuthCode(@Body LoginRequest parmas);

    /**
     * 验证验证码
     *
     * @param parmas
     * @return Object
     */
    @POST("auth/code/verify")
    Observable<DataResponse<Object>> verifyAuthCode(@Body LoginRequest.Data parmas);

    /**
     * 注册
     *
     * @param parmas
     * @return User
     */
    @POST("user/register")
    Observable<DataResponse<User>> register(@Body LoginRequest parmas);

    /**
     * 修改密码
     *
     * @param parmas
     * @return User
     */
    @POST("user/forgetpwd")
    Observable<DataResponse<User>> modifyPsd(@Body LoginRequest parmas);

    /**
     * 获取首页信息
     *
     * @param parmas
     * @return HomePageInfo
     */
    @POST("homepage/docHomePageInfo")
    Observable<DataResponse<HomePageInfo>> getHomePageInfo(@Body LoginSuccess parmas);

    /**
     * 获取医生联系人
     *
     * @param parmas
     * @return List<ContractDocItem>
     */
    @POST("doctorContact/list")
    Observable<DataResponse<List<DoctorBean>>> getContractList(@Body DocContractRequestParams parmas);

    /**
     * 获取医生个人信息
     *
     * @param parmas
     * @return DoctorBean
     */
    @POST("doctor/info")
    Observable<DataResponse<DoctorBean>> getDocInfo(@Body DocContractRequestParams parmas);

    /**
     * 接受，删除添加申请，删除好友
     *
     * @param params
     * @return Object
     */
    @POST("doctorContact/operate")
    Observable<DataResponse<Object>> docOperate(@Body DocContractRequestParams params);

    /**
     * 通过电话号搜索医生
     *
     * @param params
     * @return List<ContractDocItem>
     */
    @POST("doctorContact/doctor/list")
    Observable<DataResponse<List<DoctorBean>>> searchDoc(@Body DocContractRequestParams params);

    /**
     * 申请添加好友
     *
     * @param params
     * @return
     */
    @POST("doctorContact/apply/add")
    Observable<DataResponse<Object>> applyAddDoc(@Body RequestParams params);

    /**
     * 提交意见反馈
     *
     * @param params
     * @return
     */
    @POST("user/feedback")
    Observable<DataResponse<Object>> feedback(@Body FeedBackBean params);


    /***
     *
     * 试验管理
     * @param
     */

    @POST("clinicalTrial/clinicalTrialsByDuid")
    Observable<DataResponse<List<TestingManagement>>> Testinglist(@Body TextsingRequestParams requestParams);

    /**
     *
     * @param entity
     * @return
     */
    @POST("clinicalTrial/myCtrDetailById")
    Observable<DataResponse<TestDetails>> detailslist(@Body DetailsBean entity);

}
