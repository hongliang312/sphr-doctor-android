package com.lightheart.sphr.doctor.bean;

import java.util.List;

/**
 * Created by 知足 on 2018/5/16.
 */

public class PatientRecordsBean {

        private int id;
        private int puid;
        private String isHistory1;
        private String isHistory2;
        private String isHistory3;
        private String smokeHistory;
        private String drinkHistory;
        private String maritalHistory;
        private String familyHistory;
        private long createTime;
        private String name;
        private Object birthplace;
        private int age;
        private String nation;
        private String residenceplace;
        private List<CaseHistoriesBean> caseHistories;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPuid() {
            return puid;
        }

        public void setPuid(int puid) {
            this.puid = puid;
        }

        public String getIsHistory1() {
            return isHistory1;
        }

        public void setIsHistory1(String isHistory1) {
            this.isHistory1 = isHistory1;
        }

        public String getIsHistory2() {
            return isHistory2;
        }

        public void setIsHistory2(String isHistory2) {
            this.isHistory2 = isHistory2;
        }

        public String getIsHistory3() {
            return isHistory3;
        }

        public void setIsHistory3(String isHistory3) {
            this.isHistory3 = isHistory3;
        }

        public String getSmokeHistory() {
            return smokeHistory;
        }

        public void setSmokeHistory(String smokeHistory) {
            this.smokeHistory = smokeHistory;
        }

        public String getDrinkHistory() {
            return drinkHistory;
        }

        public void setDrinkHistory(String drinkHistory) {
            this.drinkHistory = drinkHistory;
        }

        public String getMaritalHistory() {
            return maritalHistory;
        }

        public void setMaritalHistory(String maritalHistory) {
            this.maritalHistory = maritalHistory;
        }

        public String getFamilyHistory() {
            return familyHistory;
        }

        public void setFamilyHistory(String familyHistory) {
            this.familyHistory = familyHistory;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getBirthplace() {
            return birthplace;
        }

        public void setBirthplace(Object birthplace) {
            this.birthplace = birthplace;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getResidenceplace() {
            return residenceplace;
        }

        public void setResidenceplace(String residenceplace) {
            this.residenceplace = residenceplace;
        }

        public List<CaseHistoriesBean> getCaseHistories() {
            return caseHistories;
        }

        public void setCaseHistories(List<CaseHistoriesBean> caseHistories) {
            this.caseHistories = caseHistories;
        }

        public static class CaseHistoriesBean {
            /**
             * id : 17
             * puid : 8520
             * hlrRecordId : 502
             * auid : 1284
             * uploadType : HLR_CUP_T_02
             * clinicTime : 1520307780000
             * chiefComplaint : 5.入组前未使用过核苷酸类药物及6个月内未使用过其他

             抗乙肝病毒的药物
             * diagnosis : 3.筛选时血清HBVDNA阳性大于等于105copies\ml，

             4.血清总胆红素小于等于2倍正常上限
             * treatment : 1.中医辩证为气阴两虚，湿热蕴结

             2.西医诊断复合慢性乙型肝炎诊断标准
             * drugs : 每月一次
             * createTime : 1520307775000
             * livingHabit : 生活方式习俗和朋友聊天患者发表言论自由的生活
             */

            private int id;
            private int puid;
            private int hlrRecordId;
            private int auid;
            private String uploadType;
            private long clinicTime;
            private String chiefComplaint;
            private String diagnosis;
            private String treatment;
            private String drugs;
            private long createTime;
            private String livingHabit;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPuid() {
                return puid;
            }

            public void setPuid(int puid) {
                this.puid = puid;
            }

            public int getHlrRecordId() {
                return hlrRecordId;
            }

            public void setHlrRecordId(int hlrRecordId) {
                this.hlrRecordId = hlrRecordId;
            }

            public int getAuid() {
                return auid;
            }

            public void setAuid(int auid) {
                this.auid = auid;
            }

            public String getUploadType() {
                return uploadType;
            }

            public void setUploadType(String uploadType) {
                this.uploadType = uploadType;
            }

            public long getClinicTime() {
                return clinicTime;
            }

            public void setClinicTime(long clinicTime) {
                this.clinicTime = clinicTime;
            }

            public String getChiefComplaint() {
                return chiefComplaint;
            }

            public void setChiefComplaint(String chiefComplaint) {
                this.chiefComplaint = chiefComplaint;
            }

            public String getDiagnosis() {
                return diagnosis;
            }

            public void setDiagnosis(String diagnosis) {
                this.diagnosis = diagnosis;
            }

            public String getTreatment() {
                return treatment;
            }

            public void setTreatment(String treatment) {
                this.treatment = treatment;
            }

            public String getDrugs() {
                return drugs;
            }

            public void setDrugs(String drugs) {
                this.drugs = drugs;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getLivingHabit() {
                return livingHabit;
            }

            public void setLivingHabit(String livingHabit) {
                this.livingHabit = livingHabit;
            }
        }
}
