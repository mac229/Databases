package com.maciejkozlowski.databases.realm;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.Realm;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderRealm {

    private static final String TAG = "###realm";

    public static void insertCities(Context context, Realm realm) {
        realm.beginTransaction();
        realm.delete(CityRealm.class);
        final long start = System.currentTimeMillis();
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
                CityRealm city = new CityRealm(id, name, latitude, longitude);
                realm.copyToRealm(city);
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
        Log.d(TAG, "time: " + (System.currentTimeMillis() - start));
        Log.d(TAG, "insertCities: " + realm.where(CityRealm.class).count());
        realm.commitTransaction();
    }
}
