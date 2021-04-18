package com.rajendra.vacationtourapp.model;

import java.io.Serializable;

public class TopPlacesData implements Serializable {

    //    public TopPlacesData(String title, String location, String imageUrl, Float starRating, String TongQuan) {
//        this.title = title;
//        this.location = location;
//        this.imageUrl = imageUrl;
//        this.starRating = starRating;
//        this.TongQuan = TongQuan;
//    }
    public String title, location, imageUrl;
    public Float starRating;
    public String tongQuan;
    public String getTongQuan() {
        return tongQuan;
    }

    public void setTongQuan(String tongQuan) {
        this.tongQuan = tongQuan;
    }




    public TopPlacesData(String title, String location, String imageUrl, Float starRating, String tongQuan) {
        this.title = title;
        this.location = location;
        this.imageUrl = imageUrl;
        this.starRating = starRating;
        this.tongQuan = tongQuan;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getStarRating() {
        return starRating;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }


    public TopPlacesData() {
    }


}