package com.maciejkozlowski.databases.sqlite


import android.content.Context

import com.maciejkozlowski.databases.BaseLoader
import com.maciejkozlowski.databases.Timings
import com.maciejkozlowski.databases.results.ResultSet

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class CitiesLoaderSql : BaseLoader<CitySql>() {

    fun execute(context: Context, resultSet: ResultSet, database: CitiesDatabase, size: Int) {
        val cities = readFromFile(context, BaseLoader.CITIES_CSV, size)

        logger.start()
        database.set(cities)
        logger.logTime(resultSet.creating, BaseLoader.INSERT_CITIES, size)

        logger.start()
        val citySqls = database.get()
        logger.logTime(resultSet.reading, BaseLoader.READ_CITIES, size)

        logger.start()
        for (i in citySqls.indices) {
            citySqls[i].name = i.toString()
        }
        database.update(citySqls)
        logger.logTime(resultSet.updating, BaseLoader.UPDATE_CITIES, size)

        logger.start()
        database.dropAndCreate()
        logger.logTime(resultSet.deleting, BaseLoader.DELETE_CITIES, size)
    }

    override fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): CitySql {
        return CitySql(id!!, name, latitude!!, longitude!!)
    }

    override fun createTimingLogger(): Timings {
        return Timings(TAG)
    }

    companion object {

        val TAG = "sql"
    }
}
