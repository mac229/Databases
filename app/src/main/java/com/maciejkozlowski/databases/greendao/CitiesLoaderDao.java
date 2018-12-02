package com.maciejkozlowski.databases.greendao;


import android.content.Context;

import com.maciejkozlowski.databases.BaseLoader;
import com.maciejkozlowski.databases.Timings;
import com.maciejkozlowski.databases.results.ResultSet;

import java.util.List;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class CitiesLoaderDao extends BaseLoader<CityDao> {

    public static final String TAG = "dao";

    public void execute(Context context, ResultSet resultSet, CityDaoDao cityDaoDao, int size) {
        List<CityDao> cities = readFromFile(context, Companion.getCITIES_CSV(), size);

        getLogger().start();
        cityDaoDao.insertInTx(cities);
        getLogger().logTime(resultSet.getCreating(), Companion.getINSERT_CITIES(), size);

        getLogger().start();
        List<CityDao> cityDaos = cityDaoDao.loadAll();
        getLogger().logTime(resultSet.getReading(), Companion.getREAD_CITIES(), size);

        getLogger().start();
        for (int i = 0; i < cityDaos.size(); i++) {
            cityDaos.get(i).setName(String.valueOf(i));
        }
        cityDaoDao.updateInTx(cityDaos);
        getLogger().logTime(resultSet.getUpdating(), Companion.getUPDATE_CITIES(), size);

        getLogger().start();
        cityDaoDao.deleteAll();
        getLogger().logTime(resultSet.getDeleting(), Companion.getDELETE_CITIES(), size);
    }

    @Override
    protected CityDao create(Long id, String name, Double latitude, Double longitude) {
        return new CityDao(id, name, latitude, longitude);
    }

    @Override
    protected Timings createTimingLogger() {
        return new Timings(TAG);
    }
}
