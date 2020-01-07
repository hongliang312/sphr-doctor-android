package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by admin  2020/1/7/15:10
 * Describe
 * 作者 洪亮 admin
 */
public class Main6DataBean implements Serializable {
    private int imgRes;
    private String content;
    private Class activity;

    public Main6DataBean(int imgRes, String content, Class activity) {
        this.imgRes = imgRes;
        this.content = content;
        this.activity = activity;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }


    @Override
    public String toString() {
        return "MainDataBean{" +
                "imgRes=" + imgRes +
                ", content='" + content + '\'' +
                ", activity=" + activity +
                '}';
    }
}
