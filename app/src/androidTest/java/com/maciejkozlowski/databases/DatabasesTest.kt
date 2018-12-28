package com.maciejkozlowski.databases

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.objectbox.CityBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.results.ResultSet
import com.maciejkozlowski.databases.results.Saver
import com.maciejkozlowski.databases.results.TestConstants
import com.maciejkozlowski.databases.room.CitiesLoaderRoom
import com.maciejkozlowski.databases.sqlite.CitiesDatabase
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql
import io.realm.Realm
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabasesTest {

    private val resultSet = ResultSet()

    private val citiesLoaderSql = CitiesLoaderSql()
    private val citiesLoaderRoom = CitiesLoaderRoom()
    private val citiesLoaderDao = CitiesLoaderDao()
    private val citiesLoaderBox = CitiesLoaderBox()
    private val citiesLoaderRealm = CitiesLoaderRealm()

    @org.junit.Test
    fun testDatabases() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val application = context as MyApplication

        val citiesDatabase = CitiesDatabase(context)
        val cityRoomDao = application.room.getCityRoomDao()
        val cityDaoDao = application.daoSession.cityDaoDao
        val realm = Realm.getDefaultInstance()
        val cityBox = application.boxStore.boxFor(CityBox::class.java)

        TestConstants.SIZES.forEach { size ->
            for (repeat in 0 until 10) {
                Log.d("###hash", repeat.toString())
                citiesLoaderSql.execute(context, resultSet, citiesDatabase, size)
                citiesLoaderRoom.execute(context, resultSet, cityRoomDao, size)
                citiesLoaderDao.execute(context, resultSet, cityDaoDao, size)
                citiesLoaderRealm.execute(context, resultSet, realm, size)
                citiesLoaderBox.execute(context, resultSet, cityBox, size)
            }
        }

        Saver().save(resultSet)
    }
}