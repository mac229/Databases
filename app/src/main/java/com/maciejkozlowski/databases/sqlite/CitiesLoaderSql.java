package com.maciejkozlowski.databases.sqlite;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.realm.CityRealm;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderSql extends BaseLoader<CitySql> {

    private static final String TAG = "###sql";

    public void insertCities(Context context, CitiesDatabase database) {
        logger.start();
        database.dropAndCreate();
        logger.logTime(DELETE_CITIES);
//        logger.log("size " + database.get().size());

        List<CitySql> cities = readFromFile(context, CITIES_CSV);

        logger.start();
        database.set(cities);
        logger.logTime(INSERT_CITIES);
//        logger.log("size " + database.get().size());

        logger.start();
        List<CitySql> citySqls = database.get();
        logger.logTime(READ_CITIES);
//        logger.log("size " + database.get().size());

        logger.start();
        for (int i = 0; i < citySqls.size(); i++) {
            citySqls.get(i).setName(String.valueOf(i));
        }
        database.update(citySqls);
        logger.logTime(UPDATE_CITIES);
//        logger.log("size " + database.get().size());
    }

    @Override
    protected CitySql create(Long id, String name, Double latitude, Double longitude) {
        return new CitySql(id, name, latitude, longitude);
    }

    @Override
    protected Timings createTimingLogger() {
        return new Timings(TAG);
    }
}
