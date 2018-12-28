package com.maciejkozlowski.databases.room


import android.content.Context

import com.maciejkozlowski.databases.BaseLoader
import com.maciejkozlowski.databases.Timings
import com.maciejkozlowski.databases.results.ResultSet

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class CitiesLoaderRoom : BaseLoader<CityRoom>() {

    fun execute(context: Context, resultSet: ResultSet, database: CityRoomDao, size: Int) {
        val cities = readFromFile(context, CITIES_CSV, size)

        logger.start()
        database.insertAll(cities)
        logger.logTime(resultSet.creating, INSERT_CITIES, size)

        logger.start()
        val cityRoomList = database.getAll()
        logger.logTime(resultSet.reading, READ_CITIES, size)

        logger.start()
        for (i in cityRoomList.indices) {
            cityRoomList[i].name = i.toString()
        }
        database.update(cityRoomList)
        logger.logTime(resultSet.updating, UPDATE_CITIES, size)

        logger.start()
        database.deleteAll()
        logger.logTime(resultSet.deleting, DELETE_CITIES, size)
    }

    override fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): CityRoom {
        return CityRoom(id!!, name, latitude!!, longitude!!)
    }

    override fun createTimingLogger(): Timings {
        return Timings(TAG)
    }

    companion object {

        const val TAG = "room"
    }
}
