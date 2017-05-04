package com.maciejkozlowski.databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao;
import com.maciejkozlowski.databases.greendao.CityDaoDao;
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox;
import com.maciejkozlowski.databases.objectbox.CityBox;
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm;
import com.maciejkozlowski.databases.sqlite.CitiesDatabase;
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql;

import io.objectbox.Box;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication application = (MyApplication) getApplication();

        Box<CityBox> cityBox = application.getBoxStore().boxFor(CityBox.class);
        CitiesLoaderBox.insertCities(this, cityBox);

        CityDaoDao cityDaoDao = application.getDaoSession().getCityDaoDao();
        CitiesLoaderDao.insertCities(this, cityDaoDao);

        Realm realm = application.getRealm();
        CitiesLoaderRealm.insertCities(this, realm);

        CitiesDatabase citiesDatabase = new CitiesDatabase(this);
        CitiesLoaderSql.insertCities(this, citiesDatabase);
    }
}
