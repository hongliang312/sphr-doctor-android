package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by 知足 on 2018/5/11.
 */

public class DetailsBean implements Serializable{

    private String duid;
    private String id;

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
