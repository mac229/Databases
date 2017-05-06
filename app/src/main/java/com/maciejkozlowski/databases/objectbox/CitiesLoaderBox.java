package com.maciejkozlowski.databases.objectbox;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderBox extends BaseLoader<CityBox> {

    private static final String TAG = "###box";

    public void insertCities(Context context, Box<CityBox> boxStore) {
        boxStore.removeAll();
        List<CityBox> cities = read(context);

        timingLogger.start();
        boxStore.put(cities);
        timingLogger.log(INSERT_CITIES);
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
