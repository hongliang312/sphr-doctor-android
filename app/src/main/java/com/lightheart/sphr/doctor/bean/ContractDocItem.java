package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class ContractDocItem implements Serializable {
    /**
     * id : 57
     * contUid : 8514
     * contName : 帅锅
     * imgUrl : http://www.lightheart.com.cn/files/20171220/fb6291a59e0d47339c04bb5666448716.jpeg
     */

    private int id;
    private int contUid;
    private String contName;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContUid() {
        return contUid;
    }

    public void setContUid(int contUid) {
        this.contUid = contUid;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
