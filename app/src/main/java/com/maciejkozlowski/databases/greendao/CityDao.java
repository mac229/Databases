package com.maciejkozlowski.databases.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
@Entity
public class CityDao {

    @Id
    private long id;
    private String name;
    private double latitude;
    private double longitude;

    @Keep
    public CityDao(long cityId, String name, double latitude, double longitude) {
        this.id = cityId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Generated(hash = 1672831395)
    public CityDao() {
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
