package com.lightheart.sphr.doctor.bean;

import java.util.List;

/**
 * Created by 知足 on 2018/5/11.
 */

public class TestDetails {


    /**
     * id : 3
     * projectName : 北京皮肤测试中心项目
     * projectPi : 2
     * trialNum : 1001
     * indications : 皮肤过敏
     * bidUnit : 北京阳光欣晴科技公司
     * organizeUnit : 北京阳光欣晴科技公司
     * contractUnit : 北京阳光欣晴科技公司
     * recruitCount : 180
     * trialStage : Ⅰ期
     * trialTime : 2012-05-15~2012-06-23
     * recruitStatus : ING
     * trialPurpose : 研发新的药物
     * doctorRights : 查看本项目的医生相关权益，请先进行医生认证
     * patientRights : 研究药物（免费）|研究相关检查（免费）|  专家定期随访、检测（免费）|一定的交通补助
     * recruitStandard : 1.中医辩证为气阴两虚，湿热蕴结
     * <p>
     * 2.西医诊断复合慢性乙型肝炎诊断标准
     * <p>
     * 3.筛选时血清HBVDNA阳性大于等于105copies\ml，
     * <p>
     * 4.血清总胆红素小于等于2倍正常上限
     * <p>
     * 5.入组前未使用过核苷酸类药物及6个月内未使用过其他
     * <p>
     * 抗乙肝病毒的药物
     * excludeStandard : 同时存在除乙型肝炎病毒外的其他病毒感染者
     * <p>
     * 2.药物、酒精、自身免疫、代谢等因素所致的肝炎
     * <p>
     * 3.合并心、肾、肺、内分泌
     * <p>
     * 4.血清总胆红素小于等于2倍正常上限
     * <p>
     * 5.入组前未使用过核苷酸类药物及6个月内未使用过其他
     * <p>
     * 抗乙肝病毒的药物
     * bidName : 杨梅
     * fundSource : 自筹
     * consultTel : 18811374467
     * createTime : 1513147852000
     * ctrSiteList : null
     * ctrSiteAssignments : [{"id":null,"ctrClinicalTrialId":null,"ctrSiteId":null,"projectPi":null,"projectStatus":"NEW","plannedNum":180,"involvedNum":90,"exitedNum":45,"isStart":"Y","contacts":"谷月翠（测试）","contactWay":"13261037286","createTime":null,"siteName":"北京皮肤测试中心","piName":"谷月翠（测试）","researcher":null,"projectStatusName":"尚未开始"}]
     * applyStatus : null
     * piname : null
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
    private Object ctrSiteList;
    private Object applyStatus;
    private Object piname;
    private List<CtrSiteAssignmentsBean> ctrSiteAssignments;

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

    public Object getCtrSiteList() {
        return ctrSiteList;
    }

    public void setCtrSiteList(Object ctrSiteList) {
        this.ctrSiteList = ctrSiteList;
    }

    public Object getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Object applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Object getPiname() {
        return piname;
    }

    public void setPiname(Object piname) {
        this.piname = piname;
    }

    public List<CtrSiteAssignmentsBean> getCtrSiteAssignments() {
        return ctrSiteAssignments;
    }

    public void setCtrSiteAssignments(List<CtrSiteAssignmentsBean> ctrSiteAssignments) {
        this.ctrSiteAssignments = ctrSiteAssignments;
    }

    public static class CtrSiteAssignmentsBean {
        /**
         * id : null
         * ctrClinicalTrialId : null
         * ctrSiteId : null
         * projectPi : null
         * projectStatus : NEW
         * plannedNum : 180
         * involvedNum : 90
         * exitedNum : 45
         * isStart : Y
         * contacts : 谷月翠（测试）
         * contactWay : 13261037286
         * createTime : null
         * siteName : 北京皮肤测试中心
         * piName : 谷月翠（测试）
         * researcher : null
         * projectStatusName : 尚未开始
         */

        private int id;
        private int ctrClinicalTrialId;
        private int ctrSiteId;
        private String projectPi;
        private String projectStatus;
        private double plannedNum;
        private double involvedNum;
        private int exitedNum;
        private String isStart;
        private String contacts;
        private String contactWay;
        private long createTime;
        private String siteName;
        private String piName;
        private String researcher;
        private String projectStatusName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCtrClinicalTrialId() {
            return ctrClinicalTrialId;
        }

        public void setCtrClinicalTrialId(int ctrClinicalTrialId) {
            this.ctrClinicalTrialId = ctrClinicalTrialId;
        }

        public int getCtrSiteId() {
            return ctrSiteId;
        }

        public void setCtrSiteId(int ctrSiteId) {
            this.ctrSiteId = ctrSiteId;
        }

        public String getProjectPi() {
            return projectPi;
        }

        public void setProjectPi(String projectPi) {
            this.projectPi = projectPi;
        }

        public String getProjectStatus() {
            return projectStatus;
        }

        public void setProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
        }

        public double getPlannedNum() {
            return plannedNum;
        }

        public void setPlannedNum(double plannedNum) {
            this.plannedNum = plannedNum;
        }

        public double getInvolvedNum() {
            return involvedNum;
        }

        public void setInvolvedNum(double involvedNum) {
            this.involvedNum = involvedNum;
        }

        public int getExitedNum() {
            return exitedNum;
        }

        public void setExitedNum(int exitedNum) {
            this.exitedNum = exitedNum;
        }

        public String getIsStart() {
            return isStart;
        }

        public void setIsStart(String isStart) {
            this.isStart = isStart;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getPiName() {
            return piName;
        }

        public void setPiName(String piName) {
            this.piName = piName;
        }

        public String getResearcher() {
            return researcher;
        }

        public void setResearcher(String researcher) {
            this.researcher = researcher;
        }

        public String getProjectStatusName() {
            return projectStatusName;
        }

        public void setProjectStatusName(String projectStatusName) {
            this.projectStatusName = projectStatusName;
        }
    }

}
