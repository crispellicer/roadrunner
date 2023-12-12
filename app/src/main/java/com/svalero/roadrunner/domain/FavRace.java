package com.svalero.roadrunner.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavRace {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String distance;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private double registrationPrice;
    @ColumnInfo
    private String raceDate;
    @ColumnInfo
    private double longitude;
    @ColumnInfo
    private double latitude;

    public FavRace(long id, String name, String distance, String type, String city, double registrationPrice, String raceDate) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.type = type;
        this.city = city;
        this.registrationPrice = registrationPrice;
        this.raceDate = raceDate;
    }

    public FavRace(long id, String name, String distance, String type, String city, double registrationPrice, String raceDate, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.type = type;
        this.city = city;
        this.registrationPrice = registrationPrice;
        this.raceDate = raceDate;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public FavRace(String name, String distance, String type, String city, double registrationPrice, String raceDate, double longitude, double latitude) {
        this.name = name;
        this.distance = distance;
        this.type = type;
        this.city = city;
        this.registrationPrice = registrationPrice;
        this.raceDate = raceDate;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public FavRace() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRegistrationPrice() {
        return registrationPrice;
    }

    public void setRegistrationPrice(double registrationPrice) {
        this.registrationPrice = registrationPrice;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
