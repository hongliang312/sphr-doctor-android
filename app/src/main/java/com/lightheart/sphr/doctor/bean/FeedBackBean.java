package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-12.
 * Description :
 */

public class FeedBackBean implements Serializable {

    /**
     * "uid":this.myUid,
     * "content":this.inputStr,
     * "mobileModel":"mobileModel",
     * "mobileSystem":"mobileSystem",
     * "appVersion":"appVersion"
     */

    private int uid;
    private String content;
    private String mobileModel;
    private String mobileSystem;
    private String appVersion;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getMobileSystem() {
        return mobileSystem;
    }

    public void setMobileSystem(String mobileSystem) {
        this.mobileSystem = mobileSystem;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
