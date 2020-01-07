package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-22.
 * Description :
 */

public class HospitalsModel implements Serializable {
    /**
     * address : null
     * districtId : 74
     * id : 832
     * level : null
     * logoImg : null
     * name : 东莞市人民医院
     * officialWebsite : null
     * summary : null
     * tel : null
     */

    private Object address;
    private int districtId;
    private int id;
    private Object level;
    private Object logoImg;
    private String name;
    private Object officialWebsite;
    private Object summary;
    private Object tel;

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public Object getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(Object logoImg) {
        this.logoImg = logoImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(Object officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public Object getTel() {
        return tel;
    }

    public void setTel(Object tel) {
        this.tel = tel;
    }

}
