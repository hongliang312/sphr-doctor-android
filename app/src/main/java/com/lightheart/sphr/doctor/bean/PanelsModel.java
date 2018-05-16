package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-14.
 * Description :
 */

public class PanelsModel implements Serializable {


    private long creator;
    private List<DoctorDetail> doctorList;
    private String doctorName;
    private int dtmAroId;
    private String dtmAroName;
    private String dtmAroSummary;
    private String imgUrl;
    private int duid;
    private int id;

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    private boolean isAdded;

    public PanelsModel(long creator, List<DoctorDetail> doctorList, String doctorName, int dtmAroId, String dtmAroName, String dtmAroSummary, String imgUrl, int duid, int id) {
        this.creator = creator;
        this.doctorList = doctorList;
        this.doctorName = doctorName;
        this.dtmAroId = dtmAroId;
        this.dtmAroName = dtmAroName;
        this.dtmAroSummary = dtmAroSummary;
        this.imgUrl = imgUrl;
        this.duid = duid;
        this.id = id;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public List<DoctorDetail> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorDetail> doctorList) {
        this.doctorList = doctorList;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDtmAroId() {
        return dtmAroId;
    }

    public void setDtmAroId(int dtmAroId) {
        this.dtmAroId = dtmAroId;
    }

    public String getDtmAroName() {
        return dtmAroName;
    }

    public void setDtmAroName(String dtmAroName) {
        this.dtmAroName = dtmAroName;
    }

    public String getDtmAroSummary() {
        return dtmAroSummary;
    }

    public void setDtmAroSummary(String dtmAroSummary) {
        this.dtmAroSummary = dtmAroSummary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public static class DoctorDetail implements Serializable {

        public int duid;
        public String doctorName;
        public String imgUrl;
        public String hospitalName;
        public String departmentName;
        public String districtName;
        public String titleName;
        public String majorIn;
        public String summary;
        public int operationNum;
        public int outpatientNum;
        public int dtmAroId;
        public int hospitalId;

    }

}
