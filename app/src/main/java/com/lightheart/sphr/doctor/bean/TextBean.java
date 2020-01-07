package com.lightheart.sphr.doctor.bean;

import java.io.Serializable;

/**
 * Created by admin  2019/11/26/14:24
 * Describe
 * 作者 洪亮 admin
 */
public class TextBean implements Serializable {
    public String name;
    public String title;
    public String img;
    public String price;
    public String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
