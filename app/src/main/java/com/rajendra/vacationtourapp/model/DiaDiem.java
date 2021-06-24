package com.rajendra.vacationtourapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DiaDiem implements Serializable {

    //    public TopPlacesData(String title, String location, String imageUrl, Float starRating, String TongQuan) {
//        this.title = title;
//        this.location = location;
//        this.imageUrl = imageUrl;
//        this.starRating = starRating;
//        this.TongQuan = TongQuan;
//    }
    public String title, location, imageUrl;
    public Float starRating;
    public String tongQuan,id;





    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String vitri;
    public String getTongQuan() {
        return tongQuan;
    }

    public void setTongQuan(String tongQuan) {
        this.tongQuan = tongQuan;
    }

    public DiaDiem(String title, String location, String imageUrl, Float starRating, String tongQuan, String vitri, String id ){
        this.title = title;
        this.location = location;
        this.imageUrl = imageUrl;
        this.starRating = starRating;
        this.tongQuan = tongQuan;
        this.vitri=vitri;
        this.id=id;

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


    public DiaDiem() {
    }


}