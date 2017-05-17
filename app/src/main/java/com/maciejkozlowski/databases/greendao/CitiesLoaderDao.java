package com.maciejkozlowski.databases.greendao;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;

import java.util.List;
import java.util.Random;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderDao extends BaseLoader<CityDao> {

    private static final String TAG = "###dao";

    public void insertCities(Context context, CityDaoDao cityDaoDao) {
        logger.start();
        cityDaoDao.deleteAll();
        logger.logTime(DELETE_CITIES);
//        logger.log("size " + cityDaoDao.count());

        List<CityDao> cities = readFromFile(context, CITIES_CSV);

        logger.start();
        cityDaoDao.insertInTx(cities);
        logger.logTime(INSERT_CITIES);
//        logger.log("size " + cityDaoDao.count());

        logger.start();
        List<CityDao> cityDaos = cityDaoDao.loadAll();
        logger.logTime(READ_CITIES);
//        logger.log("size " + cityDaoDao.count());

        logger.start();
        for (int i = 0; i < cityDaos.size(); i++) {
            cityDaos.get(i).setName(String.valueOf(i));
        }
        cityDaoDao.updateInTx(cityDaos);
        logger.logTime(UPDATE_CITIES);
//        logger.log("size " + cityDaoDao.count());
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
