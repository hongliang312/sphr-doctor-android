package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-4-16.
 * Description :
 */

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public String data;
    public boolean encrpyt = false;

    public static class Data implements Serializable {

        /**
         * 登录注册
         */
        public String mobile;
        public String password;
        public int usrType = 1;
//        public String token = "";
        public String termType = "ADR";
        // 发送验证码
        public int smsType;
        public String code;// 验证码

    }

}
