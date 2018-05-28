package com.lightheart.sphr.doctor.bean;

public class ConsultingListBean {


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
