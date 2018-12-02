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
        List<CityBox> cities = readFromFile(context, Companion.getCITIES_CSV(), size);

        getLogger().start();
        boxStore.put(cities);
        getLogger().logTime(resultSet.getCreating(), Companion.getINSERT_CITIES(), size);

        getLogger().start();
        List<CityBox> citiesBox = boxStore.getAll();
        getLogger().logTime(resultSet.getReading(), Companion.getREAD_CITIES(), size);


        getLogger().start();
        for (int i = 0; i < citiesBox.size(); i++) {
            citiesBox.get(i).setName(String.valueOf(i));
        }
        boxStore.put(citiesBox);
        getLogger().logTime(resultSet.getUpdating(), Companion.getUPDATE_CITIES(), size);

        getLogger().start();
        boxStore.removeAll();
        getLogger().logTime(resultSet.getDeleting(), Companion.getDELETE_CITIES(), size);
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
