package com.maciejkozlowski.databases.sqlite;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderSql extends BaseLoader<CitySql> {

    private static final String TAG = "###sql";

    public void insertCities(Context context, CitiesDatabase database) {
        database.dropAndCreate();
        List<CitySql> cities = read(context);

        timingLogger.start();
        database.set(cities);
        timingLogger.log(INSERT_CITIES);
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
