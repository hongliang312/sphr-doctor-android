package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-16.
 * Description : 创建专家组
 */

public class CreatePanelParam implements Serializable {

    /**
     * public dtmAroName: string,
     * public dtmAroSummary: string,
     * public createDuid: number,
     * public createDoctorName: string,
     * public doctorList: Array<Contact>,
     * public diagnosisArray: Array<string>
     */

    private String dtmAroName;
    private String dtmAroSummary;
    private int createDuid;
    private String createDoctorName;
    private List<CreatePanelDoctorParam> doctorList;
    private List<Integer> diagnosisArray;

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

    public int getCreateDuid() {
        return createDuid;
    }

    public void setCreateDuid(int createDuid) {
        this.createDuid = createDuid;
    }

    public String getCreateDoctorName() {
        return createDoctorName;
    }

    public void setCreateDoctorName(String createDoctorName) {
        this.createDoctorName = createDoctorName;
    }

    public List<CreatePanelDoctorParam> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<CreatePanelDoctorParam> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Integer> getDiagnosisArray() {
        return diagnosisArray;
    }

    public void setDiagnosisArray(List<Integer> diagnosisArray) {
        this.diagnosisArray = diagnosisArray;
    }
}
