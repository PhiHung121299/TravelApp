package com.rajendra.vacationtourapp.model;

public class RecentsData {

    String placeName;
    String countryName;
    public Float starRating;
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RecentsData(String placeName, String countryName, Float starRating, String imageUrl) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.starRating = starRating;
        this.imageUrl = imageUrl;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Float getStarRating() {
        return starRating;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }
}
