package com.maciejkozlowski.databases.objectbox;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.greendao.CityDao;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderBox extends BaseLoader<CityBox> {

    private static final String TAG = "###box";

    public void insertCities(Context context, Box<CityBox> boxStore) {
        logger.start();
        boxStore.removeAll();
        logger.logTime(DELETE_CITIES);
//        logger.log("size " + boxStore.count());

        List<CityBox> cities = readFromFile(context, CITIES_CSV);

        logger.start();
        boxStore.put(cities);
        logger.logTime(INSERT_CITIES);
//        logger.log("size " + boxStore.count());

        logger.start();
        List<CityBox> citiesBox = boxStore.getAll();
        logger.logTime(READ_CITIES);
//        logger.log("size " + boxStore.count());


        logger.start();
        for (int i = 0; i < citiesBox.size(); i++) {
            citiesBox.get(i).setName(String.valueOf(i));
        }
        boxStore.put(citiesBox);
        logger.logTime(UPDATE_CITIES);
//        logger.log("size " + boxStore.count());
//        logger.log(boxStore.get(1L).getName());
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
