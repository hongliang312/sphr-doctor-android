package com.guwu.common.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin  2020/1/7/15:53
 * Describe
 * 作者 洪亮 admin
 * 数据模型
 */
public class AddressBean implements Serializable {

    private List<AddressItemBean> prov;
    private List<AddressItemBean> city;
    private List<AddressItemBean> area;

    public List<AddressItemBean> getProvince() {
        return prov;
    }

    public void setProvince(List<AddressItemBean> province) {
        this.prov = province;
    }

    public List<AddressItemBean> getCity() {
        return city;
    }

    public void setCity(List<AddressItemBean> city) {
        this.city = city;
    }

    public List<AddressItemBean> getDistrict() {
        return area;
    }

    public void setDistrict(List<AddressItemBean> district) {
        this.area = district;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "prov=" + prov +
                ", city=" + city +
                ", area=" + area +
                '}';
    }

    public static class AddressItemBean implements Serializable {
        private String id;
        private String name;
        private String pid;

        public String getI() {
            return id;
        }

        public void setI(String i) {
            this.id = i;
        }

        public String getN() {
            return name;
        }

        public void setN(String n) {
            this.name = n;
        }

        public String getP() {
            return pid;
        }

        public void setP(String p) {
            this.pid = p;
        }

        @Override
        public String toString() {
            return "AddressItemBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", pid='" + pid + '\'' +
                    '}';
        }
    }
}
