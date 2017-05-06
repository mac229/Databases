package com.maciejkozlowski.databases.realm;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderRealm extends BaseLoader<CityRealm> {

    private static final String TAG = "###realm";

    public void insertCities(Context context, Realm realm) {
        realm.beginTransaction();
        realm.delete(CityRealm.class);
        List<CityRealm> cities = read(context);

        timingLogger.start();
        realm.copyToRealm(cities);
        realm.commitTransaction();
        timingLogger.log(INSERT_CITIES);
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
