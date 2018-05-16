package com.lightheart.sphr.doctor.bean;

import java.util.List;

/**
 * Created by 知足 on 2018/5/14.
 */

public class UntreatedBean {


        private String name;
        private String content;
        private long consultDate;
        private int consultId;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }


        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }

        public long getConsultDate()
        {
            return consultDate;
        }

        public void setConsultDate(long consultDate)
        {
            this.consultDate = consultDate;
        }

        public int getConsultId()
        {
            return consultId;
        }

        public void setConsultId(int consultId)
        {
            this.consultId = consultId;
        }

}
