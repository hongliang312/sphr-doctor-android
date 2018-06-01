package com.lightheart.sphr.doctor.bean;

import java.util.List;

public class HomeConsultSubDetail {


        private String content;
        private String reply;
        private String consultStatus;
        private int puid;
        private List<ImgsBean> imgs;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getConsultStatus() {
            return consultStatus;
        }

        public void setConsultStatus(String consultStatus) {
            this.consultStatus = consultStatus;
        }

        public int getPuid() {
            return puid;
        }

        public void setPuid(int puid) {
            this.puid = puid;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * id : 182
             * mediaName :
             * mediaType : img
             * mediaUrl : http://172.61.1.63//files/20170921/ab1fdb9ac3334342aab7cecb0fd17281
             * serType : UTOPIA07
             * linkId : 89
             * displayOrder : 0
             * displayStatus : Y
             * createTime : 1505930839000
             */

            private int id;
            private String mediaName;
            private String mediaType;
            private String mediaUrl;
            private String serType;
            private int linkId;
            private int displayOrder;
            private String displayStatus;
            private long createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMediaName() {
                return mediaName;
            }

            public void setMediaName(String mediaName) {
                this.mediaName = mediaName;
            }

            public String getMediaType() {
                return mediaType;
            }

            public void setMediaType(String mediaType) {
                this.mediaType = mediaType;
            }

            public String getMediaUrl() {
                return mediaUrl;
            }

            public void setMediaUrl(String mediaUrl) {
                this.mediaUrl = mediaUrl;
            }

            public String getSerType() {
                return serType;
            }

            public void setSerType(String serType) {
                this.serType = serType;
            }

            public int getLinkId() {
                return linkId;
            }

            public void setLinkId(int linkId) {
                this.linkId = linkId;
            }

            public int getDisplayOrder() {
                return displayOrder;
            }

            public void setDisplayOrder(int displayOrder) {
                this.displayOrder = displayOrder;
            }

            public String getDisplayStatus() {
                return displayStatus;
            }

            public void setDisplayStatus(String displayStatus) {
                this.displayStatus = displayStatus;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

        }

}
