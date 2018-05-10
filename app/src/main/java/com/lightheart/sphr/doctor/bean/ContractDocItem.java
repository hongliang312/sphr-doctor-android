package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class ContractDocItem implements Serializable {
    /**
     * 医生好友列表使用
     * <p>
     * id : 57
     * contUid : 8514
     * contName : 帅锅
     * imgUrl : http://www.lightheart.com.cn/files/20171220/fb6291a59e0d47339c04bb5666448716.jpeg
     */

    private int id;
    private int contUid;
    private String contName;
    private String imgUrl;
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

    private String realName;
    private String mobile;
    private String majorIn;
    private String summary;
    private String hospitalName;
    private String departmentName;
    private String titleName;
    private boolean friend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContUid() {
        return contUid;
    }

    public void setContUid(int contUid) {
        this.contUid = contUid;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
