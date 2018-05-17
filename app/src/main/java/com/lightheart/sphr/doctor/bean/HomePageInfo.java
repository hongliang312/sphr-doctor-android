package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-4-24.
 * Description :
 */

public class HomePageInfo implements Serializable {


    /**
     * clientType : D
     * bannerList : [{"id":142,"imgUrl":"http://172.61.1.63/files/20180211/43ebd771f95c4c219ba80db999bff94a.png","linkUrl":"http://172.61.1.203:8080/sosoapi-web/auth/proj/info.htm?projId=3","title":"","content":"","displayOrder":3,"isDisplay":"Y","createTime":1523344938000,"clientType":"D"},{"id":143,"imgUrl":"http://172.61.1.63/files/20180211/2b99f54f55a746b9bf4359e15c7e7079.png","linkUrl":"http://172.61.1.203:8080/sosoapi-web/auth/proj/info.htm?projId=3","title":"","content":"","displayOrder":4,"isDisplay":"Y","createTime":1523344938000,"clientType":"D"},{"id":144,"imgUrl":"http://172.61.1.63/files/20180211/343f3753ae134c0d9ae7d873ad2844b7.png","linkUrl":"http://172.61.1.203:8080/sosoapi-web/auth/proj/info.htm?projId=3","title":"","content":"","displayOrder":5,"isDisplay":"Y","createTime":1523344938000,"clientType":"D"}]
     * articleList : [{"id":33,"sourceType":"AN","clientType":"DR","articleType":"PRT_ATCL_T_01","articleStatus":"PRT_ATCL_S_PUB","title":"心血管心血管心血管","author":0,"imgUrl":"http://172.61.1.63/files/20180201/9c8050ed42134184b16fe6be7d874610.jpeg","articleUrl":"http://172.61.1.63/sphr-callcenter/index.html#/assistant_index/information","articleSource":"","visitNum":0,"creator":1273,"createTime":1517471392000,"modifier":1273,"modifyTime":1517471984000,"articleContent":null},{"id":31,"sourceType":"AN","clientType":"DR","articleType":"PRT_ATCL_T_01","articleStatus":"PRT_ATCL_S_PUB","title":"皮肤过敏性问题","author":0,"imgUrl":"http://172.61.1.63/files/20171215/6b1b46a3b1024d839b8598c9156cf7bb.png","articleUrl":"http://mp.weixin.qq.com/s/_ocfLxpSqQJCYqYOj49bZg","articleSource":"","visitNum":0,"creator":1284,"createTime":1513324735000,"modifier":1284,"modifyTime":1513324961000,"articleContent":null},{"id":4,"sourceType":"AN","clientType":"DR","articleType":"PRT_ATCL_DR_02","articleStatus":"PRT_ATCL_S_PUB","title":"冠心病患者要警惕牙齿脱落","author":0,"imgUrl":"http://172.16.1.61:8080/dima-file/files/20160727/36246e587fc445f7b9a5b4e7ccb16c72.jpeg","articleUrl":"http://mp.weixin.qq.com/s?__biz=MzAwOTczNzAwNw==&tempkey=TAtCcNbdyfGzMICOfyvk7eRx7hWCY7QWYMEBW55Q0dkyPwXPHc2wCtdnkDkjZaWB%2F7ngRqmeGIANPJg1LbXn44Z9xXR0x6IFMjisQ%2FgWBPFfYUp%2FCC4Yssw2xpffG4dLUdRY%2F7jiWoM7JTU4I9e5zw%3D%3D&#rd","articleSource":"","visitNum":29,"creator":1,"createTime":1470318853000,"modifier":1,"modifyTime":1470318864000,"articleContent":null},{"id":3,"sourceType":"AN","clientType":"DR","articleType":"PRT_ATCL_DR_01","articleStatus":"PRT_ATCL_S_PUB","title":"冠心病患者诊后管理：知\u201c病\u201d知\u201c己\u201d，健康常有","author":0,"imgUrl":"http://172.16.1.61:8080/dima-file/files/20160727/762d52b9a8404c81bc08d451f0341c60.jpeg","articleUrl":"http://mp.weixin.qq.com/s?__biz=MzAwOTczNzAwNw==&tempkey=TAtCcNbdyfGzMICOfyvk7eRx7hWCY7QWYMEBW55Q0dkyPwXPHc2wCtdnkDkjZaWB%2F7ngRqmeGIANPJg1LbXn44Z9xXR0x6IFMjisQ%2FgWBPFfYUp%2FCC4Yssw2xpffG4dLUdRY%2F7jiWoM7JTU4I9e5zw%3D%3D&#rd","articleSource":"","visitNum":73,"creator":1,"createTime":1469612534000,"modifier":1,"modifyTime":1469612624000,"articleContent":null}]
     * noticeList : [{"id":1,"clientType":"D","title":"123","content":"123","displayOrder":0,"isDisplay":"Y","createTime":1509330296000},{"id":2,"clientType":"D","title":"342323","content":"22323","displayOrder":1,"isDisplay":"Y","createTime":1509330308000},{"id":3,"clientType":"D","title":"344423","content":"dsasfd","displayOrder":2,"isDisplay":"Y","createTime":1509330323000}]
     * clinicalTrialList : [{"id":14,"projectName":"2018-3-14 测试项目管理","projectPi":2,"trialNum":"001","indications":"血压","bidUnit":"北京阳光欣晴","organizeUnit":"北京阳光欣晴分公司","contractUnit":"北京阳光欣晴总公司","recruitCount":66,"trialStage":"Ⅰ期","trialTime":"2018-04-08~2018-05-29","recruitStatus":"ING","trialPurpose":"研发新药物","doctorRights":"技术支持","patientRights":"药物免费|检查免费|随访免费|交通补助","recruitStandard":"中医辩证为气阴两虚，湿热蕴结","excludeStandard":"中医辩证为气阴两虚，湿热蕴结","bidName":"张三","fundSource":"募捐","consultTel":"13261037289","createTime":1521016444000},{"id":13,"projectName":"77","projectPi":0,"trialNum":"","indications":"","bidUnit":"","organizeUnit":"","contractUnit":"","recruitCount":0,"trialStage":"","trialTime":"","recruitStatus":"ING","trialPurpose":"","doctorRights":"","patientRights":"|||","recruitStandard":"","excludeStandard":"","bidName":"","fundSource":"","consultTel":"","createTime":1521016251000}]
     */

    private String clientType;
    private List<BannerListBean> bannerList;
    private List<ArticleListBean> articleList;
    private List<NoticeListBean> noticeList;
    private List<ClinicalTrialListBean> clinicalTrialList;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ArticleListBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleListBean> articleList) {
        this.articleList = articleList;
    }

    public List<NoticeListBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeListBean> noticeList) {
        this.noticeList = noticeList;
    }

    public List<ClinicalTrialListBean> getClinicalTrialList() {
        return clinicalTrialList;
    }

    public void setClinicalTrialList(List<ClinicalTrialListBean> clinicalTrialList) {
        this.clinicalTrialList = clinicalTrialList;
    }

    public static class BannerListBean {
        /**
         * id : 142
         * imgUrl : http://172.61.1.63/files/20180211/43ebd771f95c4c219ba80db999bff94a.png
         * linkUrl : http://172.61.1.203:8080/sosoapi-web/auth/proj/info.htm?projId=3
         * title :
         * content :
         * displayOrder : 3
         * isDisplay : Y
         * createTime : 1523344938000
         * clientType : D
         */

        private int id;
        private String imgUrl;
        private String linkUrl;
        private String title;
        private String content;
        private int displayOrder;
        private String isDisplay;
        private long createTime;
        private String clientType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(String isDisplay) {
            this.isDisplay = isDisplay;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }
    }

    public static class ArticleListBean {
        /**
         * id : 33
         * sourceType : AN
         * clientType : DR
         * articleType : PRT_ATCL_T_01
         * articleStatus : PRT_ATCL_S_PUB
         * title : 心血管心血管心血管
         * author : 0
         * imgUrl : http://172.61.1.63/files/20180201/9c8050ed42134184b16fe6be7d874610.jpeg
         * articleUrl : http://172.61.1.63/sphr-callcenter/index.html#/assistant_index/information
         * articleSource :
         * visitNum : 0
         * creator : 1273
         * createTime : 1517471392000
         * modifier : 1273
         * modifyTime : 1517471984000
         * articleContent : null
         */

        private int id;
        private String sourceType;
        private String clientType;
        private String articleType;
        private String articleStatus;
        private String title;
        private int author;
        private String imgUrl;
        private String articleUrl;
        private String articleSource;
        private int visitNum;
        private int creator;
        private long createTime;
        private int modifier;
        private long modifyTime;
        private Object articleContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getArticleType() {
            return articleType;
        }

        public void setArticleType(String articleType) {
            this.articleType = articleType;
        }

        public String getArticleStatus() {
            return articleStatus;
        }

        public void setArticleStatus(String articleStatus) {
            this.articleStatus = articleStatus;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAuthor() {
            return author;
        }

        public void setAuthor(int author) {
            this.author = author;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getArticleUrl() {
            return articleUrl;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }

        public String getArticleSource() {
            return articleSource;
        }

        public void setArticleSource(String articleSource) {
            this.articleSource = articleSource;
        }

        public int getVisitNum() {
            return visitNum;
        }

        public void setVisitNum(int visitNum) {
            this.visitNum = visitNum;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getModifier() {
            return modifier;
        }

        public void setModifier(int modifier) {
            this.modifier = modifier;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Object getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(Object articleContent) {
            this.articleContent = articleContent;
        }
    }

    public static class NoticeListBean {
        /**
         * id : 1
         * clientType : D
         * title : 123
         * content : 123
         * displayOrder : 0
         * isDisplay : Y
         * createTime : 1509330296000
         */

        private int id;
        private String clientType;
        private String title;
        private String content;
        private int displayOrder;
        private String isDisplay;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(String isDisplay) {
            this.isDisplay = isDisplay;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }

    public static class ClinicalTrialListBean implements Serializable {
        /**
         * id : 14
         * projectName : 2018-3-14 测试项目管理
         * projectPi : 2
         * trialNum : 001
         * indications : 血压
         * bidUnit : 北京阳光欣晴
         * organizeUnit : 北京阳光欣晴分公司
         * contractUnit : 北京阳光欣晴总公司
         * recruitCount : 66
         * trialStage : Ⅰ期
         * trialTime : 2018-04-08~2018-05-29
         * recruitStatus : ING
         * trialPurpose : 研发新药物
         * doctorRights : 技术支持
         * patientRights : 药物免费|检查免费|随访免费|交通补助
         * recruitStandard : 中医辩证为气阴两虚，湿热蕴结
         * excludeStandard : 中医辩证为气阴两虚，湿热蕴结
         * bidName : 张三
         * fundSource : 募捐
         * consultTel : 13261037289
         * createTime : 1521016444000
         */

        private int id;
        private String projectName;
        private int projectPi;
        private String trialNum;
        private String indications;
        private String bidUnit;
        private String organizeUnit;
        private String contractUnit;
        private int recruitCount;
        private String trialStage;
        private String trialTime;
        private String recruitStatus;
        private String trialPurpose;
        private String doctorRights;
        private String patientRights;
        private String recruitStandard;
        private String excludeStandard;
        private String bidName;
        private String fundSource;
        private String consultTel;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public int getProjectPi() {
            return projectPi;
        }

        public void setProjectPi(int projectPi) {
            this.projectPi = projectPi;
        }

        public String getTrialNum() {
            return trialNum;
        }

        public void setTrialNum(String trialNum) {
            this.trialNum = trialNum;
        }

        public String getIndications() {
            return indications;
        }

        public void setIndications(String indications) {
            this.indications = indications;
        }

        public String getBidUnit() {
            return bidUnit;
        }

        public void setBidUnit(String bidUnit) {
            this.bidUnit = bidUnit;
        }

        public String getOrganizeUnit() {
            return organizeUnit;
        }

        public void setOrganizeUnit(String organizeUnit) {
            this.organizeUnit = organizeUnit;
        }

        public String getContractUnit() {
            return contractUnit;
        }

        public void setContractUnit(String contractUnit) {
            this.contractUnit = contractUnit;
        }

        public int getRecruitCount() {
            return recruitCount;
        }

        public void setRecruitCount(int recruitCount) {
            this.recruitCount = recruitCount;
        }

        public String getTrialStage() {
            return trialStage;
        }

        public void setTrialStage(String trialStage) {
            this.trialStage = trialStage;
        }

        public String getTrialTime() {
            return trialTime;
        }

        public void setTrialTime(String trialTime) {
            this.trialTime = trialTime;
        }

        public String getRecruitStatus() {
            return recruitStatus;
        }

        public void setRecruitStatus(String recruitStatus) {
            this.recruitStatus = recruitStatus;
        }

        public String getTrialPurpose() {
            return trialPurpose;
        }

        public void setTrialPurpose(String trialPurpose) {
            this.trialPurpose = trialPurpose;
        }

        public String getDoctorRights() {
            return doctorRights;
        }

        public void setDoctorRights(String doctorRights) {
            this.doctorRights = doctorRights;
        }

        public String getPatientRights() {
            return patientRights;
        }

        public void setPatientRights(String patientRights) {
            this.patientRights = patientRights;
        }

        public String getRecruitStandard() {
            return recruitStandard;
        }

        public void setRecruitStandard(String recruitStandard) {
            this.recruitStandard = recruitStandard;
        }

        public String getExcludeStandard() {
            return excludeStandard;
        }

        public void setExcludeStandard(String excludeStandard) {
            this.excludeStandard = excludeStandard;
        }

        public String getBidName() {
            return bidName;
        }

        public void setBidName(String bidName) {
            this.bidName = bidName;
        }

        public String getFundSource() {
            return fundSource;
        }

        public void setFundSource(String fundSource) {
            this.fundSource = fundSource;
        }

        public String getConsultTel() {
            return consultTel;
        }

        public void setConsultTel(String consultTel) {
            this.consultTel = consultTel;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
