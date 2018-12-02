package com.maciejkozlowski.databases.results

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql
import java.util.*

/**
 * Created by Maciej on 2017-05-17.
 */

object TestConstants {

    var SIZES = Arrays.asList(1_000, 2_000, 5_000, 10_000, 20_000, 40_000, 60_000, 80_000, 100_000)
    var TYPES = Arrays.asList(CitiesLoaderSql.TAG, CitiesLoaderDao.TAG, CitiesLoaderRealm.TAG, CitiesLoaderBox.TAG)

}
