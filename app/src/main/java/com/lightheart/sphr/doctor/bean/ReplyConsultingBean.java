package com.lightheart.sphr.doctor.bean;

/**
 * Created by 知足 on 2018/5/18.
 */

public class ReplyConsultingBean {

    /**
     * resultcode : 200
     * resultmsg : 成功
     * event : null
     * content : 回复成功
     */

    private int resultcode;
    private String resultmsg;
    private Object event;
    private String content;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
