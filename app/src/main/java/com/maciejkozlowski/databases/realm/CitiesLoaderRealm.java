package com.maciejkozlowski.databases.realm;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.results.ResultSet;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderRealm extends BaseLoader<CityRealm> {

    public static final String TAG = "realm";

    public void execute(Context context, ResultSet resultSet, Realm realm, int size) {
        List<CityRealm> cities = readFromFile(context, CITIES_CSV, size);

        logger.start();
        realm.beginTransaction();
        realm.insert(cities);
        realm.commitTransaction();
        logger.logTime(resultSet.getCreating(), INSERT_CITIES, size);

        logger.start();
        RealmResults<CityRealm> cityRealms = realm.where(CityRealm.class).findAll();
        logger.logTime(resultSet.getReading(), READ_CITIES, size);

        logger.start();
        realm.beginTransaction();
        int i = 0;
        for (CityRealm cityRealm : cityRealms) {
            cityRealm.setName(String.valueOf(i));
            i++;
        }
        realm.commitTransaction();
        logger.logTime(resultSet.getUpdating(), UPDATE_CITIES, size);

        logger.start();
        realm.beginTransaction();
        realm.delete(CityRealm.class);
        realm.commitTransaction();
        logger.logTime(resultSet.getDeleting(), DELETE_CITIES, size);
    }

    @Override
    protected CityRealm create(Long id, String name, Double latitude, Double longitude) {
        return new CityRealm(id, name, latitude, longitude);
    }

    @Override
    protected Timings createTimingLogger() {
        return new Timings(TAG);
    }
}
