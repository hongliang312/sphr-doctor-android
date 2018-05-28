package com.lightheart.sphr.doctor.net;

import com.lightheart.sphr.doctor.base.AuthParam;
import com.lightheart.sphr.doctor.bean.Apply2PanelParam;
import com.lightheart.sphr.doctor.bean.AreaModel;
import com.lightheart.sphr.doctor.bean.ClinicalDetailParam;
import com.lightheart.sphr.doctor.bean.ClinicalRecruitModel;
import com.lightheart.sphr.doctor.bean.ClinicalSearchParam;
import com.lightheart.sphr.doctor.bean.ClinicalTrailModel;
import com.lightheart.sphr.doctor.bean.ClinicalTrialManageDetails;
import com.lightheart.sphr.doctor.bean.ConsultModel;
import com.lightheart.sphr.doctor.bean.ConsultingReplyRequestParams;
import com.lightheart.sphr.doctor.bean.CreatePanelDoctorParam;
import com.lightheart.sphr.doctor.bean.CreatePanelParam;
import com.lightheart.sphr.doctor.bean.DataResponse;
import com.lightheart.sphr.doctor.bean.DetailsBean;
import com.lightheart.sphr.doctor.bean.DiseaseModel;
import com.lightheart.sphr.doctor.bean.DocContractRequestParams;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.DoctorModel;
import com.lightheart.sphr.doctor.bean.FeedBackBean;
import com.lightheart.sphr.doctor.bean.HomePageInfo;
import com.lightheart.sphr.doctor.bean.HomePanelModel;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.bean.Invite2PanelParam;
import com.lightheart.sphr.doctor.bean.IsFriendModel;
import com.lightheart.sphr.doctor.bean.LoginRequest;
import com.lightheart.sphr.doctor.bean.LoginSuccess;
import com.lightheart.sphr.doctor.bean.ModifyPsdParam;
import com.lightheart.sphr.doctor.bean.PanelMessageModel;
import com.lightheart.sphr.doctor.bean.PanelRequestParams;
import com.lightheart.sphr.doctor.bean.PanelShareModel;
import com.lightheart.sphr.doctor.bean.PanelShareParam;
import com.lightheart.sphr.doctor.bean.PanelsModel;
import com.lightheart.sphr.doctor.bean.PatientRecordsBean;
import com.lightheart.sphr.doctor.bean.PatientRecordsRequestParams;
import com.lightheart.sphr.doctor.bean.PatientsModel;
import com.lightheart.sphr.doctor.bean.PatientsRequestParams;
import com.lightheart.sphr.doctor.bean.ConsultingReplyBean;
import com.lightheart.sphr.doctor.bean.ReplyConsultingRequestParams;
import com.lightheart.sphr.doctor.bean.RequestParams;
import com.lightheart.sphr.doctor.bean.ShareClinical2PanelParam;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetail;
import com.lightheart.sphr.doctor.bean.HomeConsultSubDetailRequestParams;
import com.lightheart.sphr.doctor.bean.TelephoneConsultBean;
import com.lightheart.sphr.doctor.bean.TextsingRequestParams;
import com.lightheart.sphr.doctor.bean.TitlesModel;
import com.lightheart.sphr.doctor.bean.ConsultingListRequestParams;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 登录
     *
     * @return DoctorBean
     */
    @POST("user/login")
    Observable<DataResponse<DoctorBean>> login(@Body LoginRequest parmas);

    /**
     * 验证码登录
     *
     * @return DoctorBean
     */
    @POST("auth/code/login")
    Observable<DataResponse<DoctorBean>> authCodeLogin(@Body LoginRequest parmas);

    /**
     * 发送验证码
     *
     * @return Object
     */
    @POST("auth/code/send")
    Observable<DataResponse<Object>> sendAuthCode(@Body LoginRequest parmas);

    /**
     * 验证验证码
     *
     * @return Object
     */
    @POST("auth/code/verify")
    Observable<DataResponse<Object>> verifyAuthCode(@Body LoginRequest.Data parmas);

    /**
     * 注册
     *
     * @return DoctorBean
     */
    @POST("user/register")
    Observable<DataResponse<DoctorBean>> register(@Body LoginRequest parmas);

    /**
     * 修改密码
     *
     * @return DoctorBean
     */
    @POST("user/forgetpwd")
    Observable<DataResponse<DoctorBean>> modifyPsd(@Body LoginRequest parmas);

    /**
     * 获取首页信息
     *
     * @return HomePageInfo
     */
    @POST("homepage/docHomePageInfo")
    Observable<DataResponse<HomePageInfo>> getHomePageInfo(@Body LoginSuccess parmas);

    /**
     * 获取医生联系人
     *
     * @return List<ContractDocItem>
     */
    @POST("doctorContact/list")
    Observable<DataResponse<List<DoctorBean>>> getContractList(@Body DocContractRequestParams parmas);

    /**
     * 获取医生个人信息
     *
     * @return DoctorBean
     */
    @POST("doctor/info")
    Observable<DataResponse<DoctorBean>> getDocInfo(@Body DocContractRequestParams parmas);

    /**
     * 接受，删除添加申请，删除好友
     *
     * @return Object
     */
    @POST("doctorContact/operate")
    Observable<DataResponse<Object>> docOperate(@Body DocContractRequestParams params);

    /**
     * 通过电话号搜索医生
     *
     * @return List<ContractDocItem>
     */
    @POST("doctorContact/doctor/list")
    Observable<DataResponse<List<DoctorBean>>> searchDoc(@Body DocContractRequestParams params);

    /**
     * 申请添加好友
     *
     * @return Object
     */
    @POST("doctorContact/apply/add")
    Observable<DataResponse<Object>> applyAddDoc(@Body RequestParams params);

    /**
     * 提交意见反馈
     *
     * @return Object
     */
    @POST("user/feedback")
    Observable<DataResponse<Object>> feedback(@Body FeedBackBean params);

    /***
     *
     * 试验管理
     */

    @POST("clinicalTrial/clinicalTrialsByDuid")
    Observable<DataResponse<List<ClinicalTrailModel>>> Testinglist(@Body TextsingRequestParams requestParams);

    /**
     * @return ClinicalTrialManageDetails
     */
    @POST("clinicalTrial/myCtrDetailById")
    Observable<DataResponse<ClinicalTrialManageDetails>> detailslist(@Body DetailsBean entity);


    /**
     * 在线咨询
     */
    @POST("consult/list")
    Observable<DataResponse<List<ConsultModel>>> getConsultList(@Body ConsultingListRequestParams untreated);


    /**
     * 患者列表
     *
     * @return List<PatientsModel>
     */
    @POST("patient/getPatientByDuid")
    Observable<DataResponse<PatientsModel>> getPatientByDuid(@Body PatientsRequestParams params);

    /**
     * 专家组列表
     *
     * @return List<PanelsModel>
     */
    @POST("dtmAro/getDtmAroAllList")
    Observable<DataResponse<HomePanelModel>> getDtmAroList(@Body PanelRequestParams params);

    /**
     * 我加入的或者感兴趣的专家组列表
     *
     * @return List<PanelsModel>
     */
    @POST("dtmAro/getDtmAroList")
    Observable<DataResponse<List<PanelsModel>>> getAllDtmAroList(@Body PanelRequestParams params);

    /**
     * 专家组共享内容列表
     *
     * @return List<PanelShareModel>
     */
    @POST("dtmAro/shareListByDtmAroId")
    Observable<DataResponse<List<PanelShareModel>>> shareListByDtmAroId(@Body PanelShareParam params);

    /**
     * 判断是否为好友
     *
     * @return IsFriendModel
     */
    @POST("doctorContact/check/relation")
    Observable<DataResponse<IsFriendModel>> checkFriend(@Body RequestParams params);

    /**
     * 获取疾病列表
     *
     * @return List<DiseaseModel>
     */
    @POST("disease/list/configed")
    Observable<DataResponse<List<DiseaseModel>>> getDiseases(@Body LoginSuccess params);

    /**
     * 申请加入专家组
     *
     * @return Object
     */
    @POST("dtmAro/addApplyDtm")
    Observable<DataResponse<Object>> addApplyDtm(@Body Apply2PanelParam params);

    /**
     * 获取临床试验招募列表
     *
     * @return clinicalTrial/searchClinicalTrial
     */
    @POST("clinicalTrial/allClinicalTrial")
    Observable<DataResponse<ClinicalRecruitModel>> getAllClinicalTrial(@Body LoginSuccess params);

    /**
     * 搜索临床试验招募
     *
     * @return List<HomePageInfo.ClinicalTrialListBean>
     */
    @POST("clinicalTrial/searchClinicalTrial")
    Observable<DataResponse<List<HomePageInfo.ClinicalTrialListBean>>> searchClinicalTrial(@Body ClinicalSearchParam params);

    /**
     * 临床试验招募详情
     *
     * @return HomePageInfo.ClinicalTrialListBean
     */
    @POST("clinicalTrial/ctrDetailById")
    Observable<DataResponse<HomePageInfo.ClinicalTrialListBean>> getctrDetails(@Body ClinicalDetailParam params);

    /**
     * 临床试验招募详情
     *
     * @return Object
     */
    @POST("clinicalTrial/apply")
    Observable<DataResponse<Object>> applyClinical(@Body ClinicalDetailParam params);

    /**
     * 临床试验招募分享到专家组
     *
     * @return Object
     */
    @POST("dtmAro/share")
    Observable<DataResponse<Object>> share2Panel(@Body ShareClinical2PanelParam params);


    /**
     * 电话详情
     */
    @POST("consult/detailById")
    Observable<DataResponse<HomeConsultSubDetail>> subDetail(@Body HomeConsultSubDetailRequestParams subDetailRequestParams);


    /**
     * 患者病历
     */
    @POST("archive/case/list")
    Observable<DataResponse<PatientRecordsBean>> clientcentlist(@Body PatientRecordsRequestParams Params);


    /**
     * 回复咨询
     */

    @POST("consult/reply")
    Observable<ConsultingReplyBean> backlist(@Body ReplyConsultingRequestParams params);

    /**
     * 获取职称
     *
     * @return List<TitlesModel>
     */
    @POST("common/title")
    Observable<DataResponse<List<TitlesModel>>> getTitles(@Body LoginSuccess params);

    /**
     * 根据地区id查询医院
     *
     * @return List<HospitalsModel>
     */
    @POST("common/getHosptialListById")
    Observable<DataResponse<List<HospitalsModel>>> getHosptialListById(@Body PanelShareParam params);

    /**
     * 查询地区一级列表
     *
     * @return List<AreaModel>
     */
    @POST("common/getProviceList")
    Observable<DataResponse<List<AreaModel>>> getProviceList(@Body LoginSuccess params);

    /**
     * 查询地区二三级列表
     *
     * @return List<AreaModel>
     */
    @POST("common/getareaListByFid")
    Observable<DataResponse<List<AreaModel>>> getAreaListByFid(@Body PanelShareParam params);

    /**
     * 查询科室列表
     *
     * @return List<AreaModel>
     */
    @POST("common/department")
    Observable<DataResponse<List<AreaModel>>> getDepartments(@Body LoginSuccess params);

    /**
     * 更新个人资料
     *
     * @return Object
     */
    @POST("doctor/info/update")
    Observable<DataResponse<Object>> updateInfo(@Body DoctorModel params);

    /**
     * 认证
     *
     * @return Object
     */
    @POST("user/doctor/auth/update2")
    Observable<DataResponse<Object>> auth(@Body AuthParam param);

    /**
     * 专家组成员邀请联系人进专家组
     *
     * @return Object
     */
    @POST("dtmAro/inviteDtms")
    Observable<DataResponse<Object>> invite2Panel(@Body Invite2PanelParam param);

    /**
     * 创建专家组 dtmAro/getdtmApplyList
     *
     * @return Object
     */
    @POST("dtmAro/addDtmAro")
    Observable<DataResponse<Object>> createPanel(@Body CreatePanelParam param);

    /**
     * 获取专家组消息 dtmAro/updateApplyDtm
     *
     * @return Object
     */
    @POST("dtmAro/getdtmApplyList")
    Observable<DataResponse<List<PanelMessageModel>>> getDtmApplyList(@Body CreatePanelDoctorParam param);

    /**
     * 同意加入专家组 user/token
     *
     * @return Object
     */
    @POST("dtmAro/updateApplyDtm")
    Observable<DataResponse<Object>> updateApplyDtm(@Body PanelMessageModel param);

    /**
     * 获取token user/changepwd
     *
     * @return String
     */
    @POST("user/token")
    Observable<DataResponse<String>> getToken(@Body LoginSuccess param);

    /**
     * 修改密码
     *
     * @return Object
     */
    @POST("user/changepwd")
    Observable<DataResponse<Object>> modifyPsd(@Body ModifyPsdParam param);

    /**
     * 咨询回复
     */
    @POST("consult/reply")
    Observable<ConsultingReplyBean> consultingreply(@Body ConsultingReplyRequestParams replyConsultingbean);

    /**
     * 电话咨询列表
     */
    @POST("consult/tel/list")
    Observable<DataResponse<List<ConsultModel>>> getTelConsultList(@Body ConsultingListRequestParams params);


    /**
     * 电话咨询详情
     * */
    @POST("consult/tel/detailById")
    Observable<DataResponse<HomeConsultSubDetail>> getTelDetailsList(@Body HomeConsultSubDetailRequestParams subDetailRequestParams);
}
