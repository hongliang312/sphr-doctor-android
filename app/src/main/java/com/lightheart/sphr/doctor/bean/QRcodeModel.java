package com.lightheart.sphr.doctor.bean;

import com.blankj.utilcode.util.SPUtils;
import com.lightheart.sphr.doctor.app.Constant;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-23.
 * Description :
 */

public class QRcodeModel implements Serializable {

    public int id = SPUtils.getInstance(Constant.SHARED_NAME).getInt(Constant.USER_KEY);
    public String lightHeartType = "DOCTOR";

}
