package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public class PanelMessageModel implements Serializable {
    /**
     * applyStatus : NEW
     * content : null申请加入您的测试专家组织
     * createTime : 1526546449000
     * dtmAroCreator : 8520
     * dtmAroId : 43
     * duid : 8520
     * id : 132
     * type : A
     */

    private String applyStatus;
    private String content;
    private long createTime;
    private int dtmAroCreator;
    private int dtmAroId;
    private int duid;
    private int id;
    private String type;

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDtmAroCreator() {
        return dtmAroCreator;
    }

    public void setDtmAroCreator(int dtmAroCreator) {
        this.dtmAroCreator = dtmAroCreator;
    }

    public int getDtmAroId() {
        return dtmAroId;
    }

    public void setDtmAroId(int dtmAroId) {
        this.dtmAroId = dtmAroId;
    }

    public int getDuid() {
        return duid;
    }

    public void setDuid(int duid) {
        this.duid = duid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
