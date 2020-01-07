package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-22.
 * Description :
 */

public class AreaModel implements Serializable {
    /**
     * fid : 0
     * fullName : 北京
     * id : 1
     * level : 1
     * name : 北京
     * orderNo : 0
     */

    private int fid;
    private String fullName;
    private int id;
    private int level;
    private String name;
    private int orderNo;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

}
