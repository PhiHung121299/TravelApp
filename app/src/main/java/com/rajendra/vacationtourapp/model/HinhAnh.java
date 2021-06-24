package com.rajendra.vacationtourapp.model;

import java.io.Serializable;

public class HinhAnh implements Serializable {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HinhAnh() {
    }

    public HinhAnh(String url) {
        this.url = url;
    }

    public String url;
//    public HinhAnh() {
//    }
//
//    public HinhAnh(String img) {
//        this.url = img;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String img) {
//        this.url = url;
//    }



}