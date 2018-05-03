package com.lightheart.sphr.doctor.bean;


import java.io.Serializable;

public class DataResponse<T> implements Serializable {

    private String resultmsg;
    private int resultcode;
    private T content;
    public String event;

    public int page;
    public int pagesize;


    public int totalPage;
    public int currentPage;
    public int total;


    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
