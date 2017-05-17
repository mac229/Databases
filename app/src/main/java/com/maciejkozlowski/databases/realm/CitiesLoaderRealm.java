package com.maciejkozlowski.databases.realm;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.objectbox.CityBox;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderRealm extends BaseLoader<CityRealm> {

    private static final String TAG = "###realm";

    public void insertCities(Context context, Realm realm) {
        logger.start();
        realm.beginTransaction();
        realm.delete(CityRealm.class);
        realm.commitTransaction();
        logger.logTime(DELETE_CITIES);
//        logger.log("size " + realm.where(CityRealm.class).count());

        List<CityRealm> cities = readFromFile(context, CITIES_CSV);

        logger.start();
        realm.beginTransaction();
        realm.insert(cities);
        realm.commitTransaction();
        logger.logTime(INSERT_CITIES);
//        logger.log("size " + realm.where(CityRealm.class).count());

        logger.start();
        RealmResults<CityRealm> cityRealms = realm.where(CityRealm.class).findAll();
        logger.logTime(READ_CITIES);
//        logger.log("size " + realm.where(CityRealm.class).count());

        logger.start();
        realm.beginTransaction();
        int i = 0;
        for (CityRealm cityRealm : cityRealms) {
            cityRealm.setName(String.valueOf(i));
            i++;
        }
        realm.commitTransaction();
        logger.logTime(UPDATE_CITIES);
//        logger.log("size " + realm.where(CityRealm.class).count());

/*        List<CityRealm> cities1 = readFromFile(context, UPDATE_CITIES_CSV);

        logger.start();
        realm.beginTransaction();
        realm.insertOrUpdate(cities1);
        realm.commitTransaction();
        logger.logTime(UPDATE_CITIES);*/
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
