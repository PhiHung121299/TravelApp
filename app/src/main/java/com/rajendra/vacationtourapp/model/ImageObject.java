package com.rajendra.vacationtourapp.model;

import java.io.Serializable;

public class ImageObject implements Serializable {
   public String url;
  public ImageObject() {
   }

    public ImageObject(String img) {
        this.url = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String img) {
        this.url = url;
    }



}
