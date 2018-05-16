package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-15.
 * Description :
 */

public class PanelShareModel implements Serializable {


    /**
     * id : 5
     * duid : 8520
     * sharerName : 谷月翠（医生测试）
     * dtmAroId : 40
     * shareTitle : 测试申请项目加入
     * shareType : DTM_SHR_T_04
     * linkId : 8
     * createTime : 1513847598000
     */

    private int id;
    private int duid;
    private String sharerName;
    private int dtmAroId;
    private String shareTitle;
    private String shareType;
    private int linkId;
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuid() {
        return duid;
    }

    public void setDuid(int duid) {
        this.duid = duid;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public int getDtmAroId() {
        return dtmAroId;
    }

    public void setDtmAroId(int dtmAroId) {
        this.dtmAroId = dtmAroId;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
