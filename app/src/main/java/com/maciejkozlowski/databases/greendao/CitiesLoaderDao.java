package com.maciejkozlowski.databases.greendao;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderDao extends BaseLoader<CityDao> {

    private static final String TAG = "###dao";

    public void insertCities(Context context, CityDaoDao cityDaoDao) {
        List<CityDao> cities = read(context);

        cityDaoDao.deleteAll();

        timingLogger.start();
        cityDaoDao.insertInTx(cities);
        timingLogger.log(INSERT_CITIES);
    }

    @Override
    protected CityDao create(Long id, String name, Double latitude, Double longitude) {
        return new CityDao(id, name, latitude, longitude);
    }

    @Override
    protected Timings createTimingLogger() {
        return new Timings(TAG);
    }
}
