package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-19.
 * Description :
 */

public class ShareClinical2PanelParam implements Serializable {


    /**
     * duid : this.duid
     * sharerName : this.sharerName
     * dtmAroId : dtmAroId
     * shareTitle : this.shareTitle
     * shareType : DTM_SHR_T_04
     * linkId : this.linkId
     */

    public int duid;
    public String sharerName;
    public int dtmAroId;
    public String shareTitle;
    public String shareType = "DTM_SHR_T_04";
    public int linkId;

}
