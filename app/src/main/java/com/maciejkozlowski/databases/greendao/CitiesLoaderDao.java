package com.maciejkozlowski.databases.greendao;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.results.ResultSet;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderDao extends BaseLoader<CityDao> {

    public static final String TAG = "dao";

    public void execute(Context context, ResultSet resultSet, CityDaoDao cityDaoDao, int size) {
        List<CityDao> cities = readFromFile(context, CITIES_CSV, size);

        logger.start();
        cityDaoDao.insertInTx(cities);
        logger.logTime(resultSet.getCreating(), INSERT_CITIES, size);

        logger.start();
        List<CityDao> cityDaos = cityDaoDao.loadAll();
        logger.logTime(resultSet.getReading(), READ_CITIES, size);

        logger.start();
        for (int i = 0; i < cityDaos.size(); i++) {
            cityDaos.get(i).setName(String.valueOf(i));
        }
        cityDaoDao.updateInTx(cityDaos);
        logger.logTime(resultSet.getUpdating(), UPDATE_CITIES, size);

        logger.start();
        cityDaoDao.deleteAll();
        logger.logTime(resultSet.getDeleting(), DELETE_CITIES, size);
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
