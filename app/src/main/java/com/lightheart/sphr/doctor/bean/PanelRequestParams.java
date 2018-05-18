package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-15.
 * Description :
 */

public class PanelRequestParams implements Serializable {

    // 专家组使用
    public String isMember; // Y时，查询自己创建和加入的专家组,N时，表示自己未加入的专家组，A时，所有专家组

    public int duid;

}
