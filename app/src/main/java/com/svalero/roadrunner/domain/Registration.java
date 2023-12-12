package com.svalero.roadrunner.domain;

public class Registration {
    private long id;
    private String raceName;
    private String distance;
    private String city;

    public Registration(long id, String raceName, String distance, String city) {
        this.id = id;
        this.raceName = raceName;
        this.distance = distance;
        this.city = city;
    }

    public Registration() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}