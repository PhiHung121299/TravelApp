package com.rajendra.vacationtourapp.model;

public class TopPlacesData {

    String placeName;
    String countryName;


    public Float starRating;
    Integer imageUrl;

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TopPlacesData(String placeName, String countryName, Float starRating, Integer imageUrl) {
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
