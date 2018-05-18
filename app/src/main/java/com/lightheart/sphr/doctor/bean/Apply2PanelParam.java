package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-17.
 * Description : 申请加入专家组
 */

public class Apply2PanelParam implements Serializable {

    /**
     * "duid" : duid,
     * "dtmAroId": group.dtmAroId,
     * "creator" : group.creator,
     * "doctorName" : group.doctorName,
     * "dtmAroName" : group.dtmAroName
     */

    public int duid;
    public int dtmAroId;
    public int creator;
    public String doctorName;
    public String dtmAroName;

}
