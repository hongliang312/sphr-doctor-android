package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-21.
 * Description : 专家组model
 */

public class HomePanelModel implements Serializable {

    public List<PanelsModel> otherGroupList;// 感兴趣的专家组
    public List<PanelsModel> owerGroupList;// 我加入的专家组

}
