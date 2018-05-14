package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-4-25.
 * Description :
 */

public class DocContractRequestParams implements Serializable {

    // pageSize: "30", pageNum: 1, duid: 8520, status: "ADD"

    public int pageSize;
    public int pageNum;
    public int duid;
    public String status;

    // 好友申请列表id
    public int id;

    // 搜索好友
    public String mobile;

}
