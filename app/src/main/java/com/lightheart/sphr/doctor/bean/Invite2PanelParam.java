package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fucp on 2018-5-24.
 * Description :
 */

public class Invite2PanelParam implements Serializable {

    /**
     * "dtmAroId"          : dtmAroId,
     * "inviteId"          : inviteId,
     * "contactList"       : contactList.toJSON()
     */

    public int dtmAroId;
    public int inviteId;
    public List<InviteDoctor> contactList;

    public static class InviteDoctor implements Serializable {
        public int contUid;
        public String contName;
    }

}
