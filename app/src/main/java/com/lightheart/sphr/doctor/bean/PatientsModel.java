package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-14.
 * Description :
 */

public class PatientsModel implements Serializable {

    public List<PatientModel> list;

    public static class PatientModel implements Serializable {

        public String patientName;
        public String sex;
        public String birth;
        public int puid;
        public int duid;
        public String portrait;
        public String discode;
        public String disdesc;

    }

}
