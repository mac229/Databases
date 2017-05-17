package com.maciejkozlowski.databases.sqlite;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.results.ResultSet;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderSql extends BaseLoader<CitySql> {

    public static final String TAG = "sql";

    public void execute(Context context, ResultSet resultSet, CitiesDatabase database, int size) {
        List<CitySql> cities = readFromFile(context, CITIES_CSV, size);

        logger.start();
        database.set(cities);
        logger.logTime(resultSet.getCreating(), INSERT_CITIES, size);

        logger.start();
        List<CitySql> citySqls = database.get();
        logger.logTime(resultSet.getReading(), READ_CITIES, size);

        logger.start();
        for (int i = 0; i < citySqls.size(); i++) {
            citySqls.get(i).setName(String.valueOf(i));
        }
        database.update(citySqls);
        logger.logTime(resultSet.getUpdating(), UPDATE_CITIES, size);

        logger.start();
        database.dropAndCreate();
        logger.logTime(resultSet.getDeleting(), DELETE_CITIES, size);
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
