package com.maciejkozlowski.databases.realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Maciej Kozłowski on 02.05.17.
 */
public class CityRealm extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private double latitude;
    private double longitude;

    public CityRealm() {
    }

    public CityRealm(long id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
