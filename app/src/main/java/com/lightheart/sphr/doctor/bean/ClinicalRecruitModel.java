package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-17.
 * Description : 临床试验招募实体
 */

public class ClinicalRecruitModel implements Serializable {

    public List<HomePageInfo.ClinicalTrialListBean> clinicalTrials;

    public List<Object> ctrSearchLogs;

}
