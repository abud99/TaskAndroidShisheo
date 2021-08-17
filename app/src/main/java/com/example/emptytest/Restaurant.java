package com.example.emptytest;

import android.net.UrlQuerySanitizer;

import java.net.URL;

public class Restaurant {
    private String name;
    private String offer;
    private String descrp;
    private String url;
    private double stars = 5;

    public Restaurant(String name, String offer, String descrp, String url) {
        this.name = name;
        this.offer = offer;
        this.descrp = descrp;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
