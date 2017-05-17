package com.maciejkozlowski.databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao;
import com.maciejkozlowski.databases.greendao.CityDaoDao;
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox;
import com.maciejkozlowski.databases.objectbox.CityBox;
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm;
import com.maciejkozlowski.databases.results.ResultSet;
import com.maciejkozlowski.databases.results.Saver;
import com.maciejkozlowski.databases.results.Test;
import com.maciejkozlowski.databases.sqlite.CitiesDatabase;
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql;

import io.objectbox.Box;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ResultSet resultSet = new ResultSet();

    private CitiesLoaderBox citiesLoaderBox = new CitiesLoaderBox();
    private CitiesLoaderDao citiesLoaderDao = new CitiesLoaderDao();
    private CitiesLoaderRealm citiesLoaderRealm = new CitiesLoaderRealm();
    private CitiesLoaderSql citiesLoaderSql = new CitiesLoaderSql();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication application = (MyApplication) getApplication();

        CitiesDatabase citiesDatabase = new CitiesDatabase(this);
        CityDaoDao cityDaoDao = application.getDaoSession().getCityDaoDao();
        Realm realm = application.getRealm();
        Box<CityBox> cityBox = application.getBoxStore().boxFor(CityBox.class);

        for (int i = 0; i < Test.SIZES.size(); i++) {
            int size = Test.SIZES.get(i);
            for (int repeat = 0; repeat < 10; repeat++) {
                citiesLoaderSql.execute(this, resultSet, citiesDatabase, size);
                citiesLoaderDao.execute(this, resultSet, cityDaoDao, size);
                citiesLoaderRealm.execute(this, resultSet, realm, size);
                citiesLoaderBox.execute(this, resultSet, cityBox, size);
            }
        }

        new Saver().save(resultSet);
    }
}
