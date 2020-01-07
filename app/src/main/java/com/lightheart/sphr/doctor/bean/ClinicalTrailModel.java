package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

public class ClinicalTrailModel implements Serializable {
    /**
     * bidName : null
     * bidUnit : 北京阳光欣晴
     * consultTel : null
     * contractUnit : null
     * createTime : null
     * doctorRights : null
     * excludeStandard : null
     * fundSource : null
     * id : 1
     * indications : 冠心病
     * organizeUnit : null
     * patientRights : null
     * projectName : 冠心病项目
     * projectPi : null
     * recruitCount : 45
     * recruitStandard : null
     * recruitStatus : null
     * trialNum : null
     * trialPurpose : null
     * trialStage : Ⅳ期
     * trialTime : null
     */

    private String bidName;
    private String bidUnit;
    private String consultTel;
    private String contractUnit;
    private long createTime;

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getBidUnit() {
        return bidUnit;
    }

    public void setBidUnit(String bidUnit) {
        this.bidUnit = bidUnit;
    }

    public String getConsultTel() {
        return consultTel;
    }

    public void setConsultTel(String consultTel) {
        this.consultTel = consultTel;
    }

    public String getContractUnit() {
        return contractUnit;
    }

    public void setContractUnit(String contractUnit) {
        this.contractUnit = contractUnit;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDoctorRights() {
        return doctorRights;
    }

    public void setDoctorRights(String doctorRights) {
        this.doctorRights = doctorRights;
    }

    public String getExcludeStandard() {
        return excludeStandard;
    }

    public void setExcludeStandard(String excludeStandard) {
        this.excludeStandard = excludeStandard;
    }

    public String getFundSource() {
        return fundSource;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getOrganizeUnit() {
        return organizeUnit;
    }

    public void setOrganizeUnit(String organizeUnit) {
        this.organizeUnit = organizeUnit;
    }

    public String getPatientRights() {
        return patientRights;
    }

    public void setPatientRights(String patientRights) {
        this.patientRights = patientRights;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPi() {
        return projectPi;
    }

    public void setProjectPi(String projectPi) {
        this.projectPi = projectPi;
    }

    public int getRecruitCount() {
        return recruitCount;
    }

    public void setRecruitCount(int recruitCount) {
        this.recruitCount = recruitCount;
    }

    public String getRecruitStandard() {
        return recruitStandard;
    }

    public void setRecruitStandard(String recruitStandard) {
        this.recruitStandard = recruitStandard;
    }

    public String getRecruitStatus() {
        return recruitStatus;
    }

    public void setRecruitStatus(String recruitStatus) {
        this.recruitStatus = recruitStatus;
    }

    public String getTrialNum() {
        return trialNum;
    }

    public void setTrialNum(String trialNum) {
        this.trialNum = trialNum;
    }

    public String getTrialPurpose() {
        return trialPurpose;
    }

    public void setTrialPurpose(String trialPurpose) {
        this.trialPurpose = trialPurpose;
    }

    public String getTrialStage() {
        return trialStage;
    }

    public void setTrialStage(String trialStage) {
        this.trialStage = trialStage;
    }

    public long getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(long trialTime) {
        this.trialTime = trialTime;
    }

    private String doctorRights;
    private String excludeStandard;
    private String fundSource;
    private int id;
    private String indications;
    private String organizeUnit;
    private String patientRights;
    private String projectName;
    private String projectPi;
    private int recruitCount;
    private String recruitStandard;
    private String recruitStatus;
    private String trialNum;
    private String trialPurpose;
    private String trialStage;
    private long trialTime;


}
