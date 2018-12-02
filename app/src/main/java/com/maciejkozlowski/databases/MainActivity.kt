package com.maciejkozlowski.databases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.objectbox.CityBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.results.ResultSet
import com.maciejkozlowski.databases.results.Saver
import com.maciejkozlowski.databases.results.Test
import com.maciejkozlowski.databases.sqlite.CitiesDatabase
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql

class MainActivity : AppCompatActivity() {

    private val resultSet = ResultSet()

    private val citiesLoaderBox = CitiesLoaderBox()
    private val citiesLoaderDao = CitiesLoaderDao()
    private val citiesLoaderRealm = CitiesLoaderRealm()
    private val citiesLoaderSql = CitiesLoaderSql()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val application = application as MyApplication

        val citiesDatabase = CitiesDatabase(this)
        val cityDaoDao = application.daoSession.cityDaoDao
        val realm = application.realm
        val cityBox = application.boxStore.boxFor(CityBox::class.java)

        for (i in Test.SIZES.indices) {
            val size = Test.SIZES[i]
            for (repeat in 0..9) {
                citiesLoaderSql.execute(this, resultSet, citiesDatabase, size)
                citiesLoaderDao.execute(this, resultSet, cityDaoDao, size)
                citiesLoaderRealm.execute(this, resultSet, realm, size)
                citiesLoaderBox.execute(this, resultSet, cityBox, size)
            }
        }

        Saver().save(resultSet)
    }
}
