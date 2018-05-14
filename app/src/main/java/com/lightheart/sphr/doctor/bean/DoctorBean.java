package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class DoctorBean implements Serializable {
    /**
     * id : 8520
     * certStatus : USR_CERT_S_SUC
     * districtId : 3
     * districtName : 北京-北京市-东城区
     * hospitalId : 4
     * hospitalName : 北京市公安医院
     * departmentId : 116
     * departmentName : 过敏科
     * titleId : 5
     * titleName : 助理医师
     * outpatientNum : 88
     * operationNum : 22
     * idCardNo : 0
     * idCardImg :
     * dqcNo :
     * dqcImg : http://www.lightheart.com.cn/files/20171221/a8b373d45748467ba60fc596ebed5cd2.jpeg
     * dpcNo :
     * dpcImg :
     * dtcNo :
     * dtcImg :
     * inviteCode : 285466
     * majorIn : 唱歌
     * summary : 从医多年，经验丰富
     * modifier : 1051
     * modifyTime : 1513849961000
     * isCtrResearcher : Y
     * realName : 谷雨
     * imgUrl : http://www.lightheart.com.cn/files/20171221/54394537bf0049d7a8e6d4e072b757a8.jpeg
     * serPrice : 1.01
     * lightHeartType : DOCTOR
     * sex : F
     * birth : 724867200000
     * isArticlePush : Y
     * isPersonalPush : Y
     */

    private int id;
    private String certStatus;// 'USR_CERT_S_SUC(成功)','USR_CERT_S_FAL(失败)','USR_CERT_S_IN(认证中)','USR_CERT_S_UN(未认证)'
    private int districtId;
    private String districtName;
    private int hospitalId;
    private String hospitalName;
    private int departmentId;
    private String departmentName;
    private int titleId;
    private String titleName;
    private int outpatientNum;
    private int operationNum;
    private int idCardNo;
    private String idCardImg;
    private String dqcNo;
    private String dqcImg;
    private String dpcNo;
    private String dpcImg;
    private String dtcNo;
    private String dtcImg;
    private String inviteCode;
    private String majorIn;
    private String summary;
    private int modifier;
    private long modifyTime;
    private String isCtrResearcher;
    private String realName;
    private String imgUrl;
    private double serPrice;
    private String lightHeartType;
    private String sex;
    private long birth;
    private String isArticlePush;
    private String isPersonalPush;


    /**
     * 医生好友列表使用
     * <p>
     * id : 57
     * contUid : 8514
     * contName : 帅锅
     * imgUrl : http://www.lightheart.com.cn/files/20171220/fb6291a59e0d47339c04bb5666448716.jpeg
     */

    private int contUid;
    private String contName;

    public int getContUid() {
        return contUid;
    }

    public void setContUid(int contUid) {
        this.contUid = contUid;
    }

    /**
     * 搜索医生列表使用
     * <p>
     * realName : ws
     * mobile : 13098989898
     * majorIn : 唱歌唱
     * summary : 很好
     * hospitalName : 首都医科大学附属北京中医医院
     * departmentName : 内肚科
     * titleName : 主任医师
     * friend : false
     */

    private String mobile;
    private boolean friend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getOutpatientNum() {
        return outpatientNum;
    }

    public void setOutpatientNum(int outpatientNum) {
        this.outpatientNum = outpatientNum;
    }

    public int getOperationNum() {
        return operationNum;
    }

    public void setOperationNum(int operationNum) {
        this.operationNum = operationNum;
    }

    public int getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(int idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
    }

    public String getDqcNo() {
        return dqcNo;
    }

    public void setDqcNo(String dqcNo) {
        this.dqcNo = dqcNo;
    }

    public String getDqcImg() {
        return dqcImg;
    }

    public void setDqcImg(String dqcImg) {
        this.dqcImg = dqcImg;
    }

    public String getDpcNo() {
        return dpcNo;
    }

    public void setDpcNo(String dpcNo) {
        this.dpcNo = dpcNo;
    }

    public String getDpcImg() {
        return dpcImg;
    }

    public void setDpcImg(String dpcImg) {
        this.dpcImg = dpcImg;
    }

    public String getDtcNo() {
        return dtcNo;
    }

    public void setDtcNo(String dtcNo) {
        this.dtcNo = dtcNo;
    }

    public String getDtcImg() {
        return dtcImg;
    }

    public void setDtcImg(String dtcImg) {
        this.dtcImg = dtcImg;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMajorIn() {
        return majorIn;
    }

    public void setMajorIn(String majorIn) {
        this.majorIn = majorIn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getIsCtrResearcher() {
        return isCtrResearcher;
    }

    public void setIsCtrResearcher(String isCtrResearcher) {
        this.isCtrResearcher = isCtrResearcher;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(double serPrice) {
        this.serPrice = serPrice;
    }

    public String getLightHeartType() {
        return lightHeartType;
    }

    public void setLightHeartType(String lightHeartType) {
        this.lightHeartType = lightHeartType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public String getIsArticlePush() {
        return isArticlePush;
    }

    public void setIsArticlePush(String isArticlePush) {
        this.isArticlePush = isArticlePush;
    }

    public String getIsPersonalPush() {
        return isPersonalPush;
    }

    public void setIsPersonalPush(String isPersonalPush) {
        this.isPersonalPush = isPersonalPush;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
