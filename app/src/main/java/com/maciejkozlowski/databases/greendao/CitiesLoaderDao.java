package com.maciejkozlowski.databases.greendao;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderDao {

    private static final String TAG = "###dao";

    public static void insertCities(Context context, CityDaoDao cityDaoDao) {
        List<CityDao> cities = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("cities.csv")));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                Long id = Long.valueOf(strings[0]);
                Double latitude = Double.valueOf(strings[2]);
                Double longitude = Double.valueOf(strings[3]);
                String name = strings[1];
                CityDao city = new CityDao(id, name, latitude, longitude);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        cityDaoDao.deleteAll();

        long start = System.currentTimeMillis();
        cityDaoDao.insertInTx(cities);

        Log.d(TAG, "time: " + (System.currentTimeMillis() - start));
        Log.d(TAG, "insertCities: " + cityDaoDao.count());
    }
}
