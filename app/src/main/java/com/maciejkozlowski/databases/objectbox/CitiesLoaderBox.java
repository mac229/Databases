package com.maciejkozlowski.databases.objectbox;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.results.ResultSet;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderBox extends BaseLoader<CityBox> {

    public static final String TAG = "box";

    public void execute(Context context, ResultSet resultSet, Box<CityBox> boxStore, int size) {
        List<CityBox> cities = readFromFile(context, CITIES_CSV, size);

        logger.start();
        boxStore.put(cities);
        logger.logTime(resultSet.getCreating(), INSERT_CITIES, size);

        logger.start();
        List<CityBox> citiesBox = boxStore.getAll();
        logger.logTime(resultSet.getReading(), READ_CITIES, size);


        logger.start();
        for (int i = 0; i < citiesBox.size(); i++) {
            citiesBox.get(i).setName(String.valueOf(i));
        }
        boxStore.put(citiesBox);
        logger.logTime(resultSet.getUpdating(), UPDATE_CITIES, size);

        logger.start();
        boxStore.removeAll();
        logger.logTime(resultSet.getDeleting(), DELETE_CITIES, size);
    }

    @Override
    protected CityBox create(Long id, String name, Double latitude, Double longitude) {
        return new CityBox(id, name, latitude, longitude);
    }

    @Override
    protected Timings createTimingLogger() {
        return new Timings(TAG);
    }
}
