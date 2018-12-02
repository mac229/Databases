package com.maciejkozlowski.databases.objectbox;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
@Entity
public class CityBox {

    @Id
    private long id;
    private long cityId;
    private String name;
    private double latitude;
    private double longitude;

    public CityBox(long cityId, String name, double latitude, double longitude) {
        this.cityId = cityId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CityBox(long id, long cityId, String name, double latitude,
            double longitude) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CityBox() {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
