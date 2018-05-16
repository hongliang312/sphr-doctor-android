package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-14.
 * Description :
 */

public class PatientsRequestParams implements Serializable {

    // 患者管理
    public int timeCategory;
    public int pageSize;
    public int pageNum;
    public int duid;

}
