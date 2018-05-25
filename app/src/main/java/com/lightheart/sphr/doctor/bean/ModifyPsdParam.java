package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by fucp on 2018-5-25.
 * Description :
 */

public class ModifyPsdParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public String data;

    public static class Data implements Serializable {

        /**
         * "{"uid":8520,
         * "password":"123456",
         * "newword":"111111",
         * "token":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdQ37dO9r/drlUdPsV5skV8kUOgEqbmZQASI60HkdsqLq6gkcOIopr9oF6xgXX0jQhffJtpXpHI/mAGUMZcwyPWO5l5ATy7UK4jyih0fZJ4VC3b0kHW8mXy4A3gPTkAoC5PIVqYGsYuwgqLwQnfn8OaVAgy3q/9lzGFFLbnBNTLwIDAQAB",
         * "termType":"IOS",
         * "encrpyt":"false"}"
         */
        public String encrpyt = "false";
        public int uid;
        public String password;
        public String newword;
        public String token = "";
        public String termType = "ADR";

    }


}
