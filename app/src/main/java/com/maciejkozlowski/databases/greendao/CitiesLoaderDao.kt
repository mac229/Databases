package com.maciejkozlowski.databases.greendao


import android.content.Context

import com.maciejkozlowski.databases.BaseLoader
import com.maciejkozlowski.databases.Timings
import com.maciejkozlowski.databases.results.ResultSet

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class CitiesLoaderDao : BaseLoader<CityDao>() {

    fun execute(context: Context, resultSet: ResultSet, cityDaoDao: CityDaoDao, size: Int) {
        val cities = readFromFile(context, CITIES_CSV, size)

        logger.start()
        cityDaoDao.insertInTx(cities)
        logger.logTime(resultSet.creating, INSERT_CITIES, size)

        logger.start()
        val cityDaos = cityDaoDao.loadAll()
        logger.logTime(resultSet.reading, READ_CITIES, size)

        logger.start()
        for (i in cityDaos.indices) {
            cityDaos[i].name = i.toString()
        }
        cityDaoDao.updateInTx(cityDaos)
        logger.logTime(resultSet.updating, UPDATE_CITIES, size)

        logger.start()
        cityDaoDao.deleteAll()
        logger.logTime(resultSet.deleting, DELETE_CITIES, size)
    }

    override fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): CityDao {
        return CityDao(id!!, name, latitude!!, longitude!!)
    }

    override fun createTimingLogger(): Timings {
        return Timings(TAG)
    }

    companion object {

        const val TAG = "dao"
    }
}
