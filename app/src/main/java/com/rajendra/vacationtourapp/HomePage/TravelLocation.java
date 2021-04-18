package com.rajendra.vacationtourapp.HomePage;

import java.io.Serializable;

public class TravelLocation implements Serializable {
    public TravelLocation(String title, String location, String imageUrl, Float starRating) {
        this.title = title;
        this.location = location;
        this.imageUrl = imageUrl;
        this.starRating = starRating;
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

    public String title,location,imageUrl;
    public Float starRating;

    public TravelLocation() {

    }
}
