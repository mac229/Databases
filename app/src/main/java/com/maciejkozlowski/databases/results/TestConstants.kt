package com.maciejkozlowski.databases.results

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.room.CitiesLoaderRoom
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql

/**
 * Created by Maciej on 2017-05-17.
 */

object TestConstants {

    var SIZES = listOf(10_000, 20_000, 30_000, 40_000, 50_000)
    var TYPES = listOf(CitiesLoaderSql.TAG, CitiesLoaderRoom.TAG, CitiesLoaderDao.TAG,
            CitiesLoaderRealm.TAG, CitiesLoaderBox.TAG)

}
