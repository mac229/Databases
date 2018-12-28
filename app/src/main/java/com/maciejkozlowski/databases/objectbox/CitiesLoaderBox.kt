package com.maciejkozlowski.databases.objectbox


import android.content.Context

import com.maciejkozlowski.databases.BaseLoader
import com.maciejkozlowski.databases.Timings
import com.maciejkozlowski.databases.results.ResultSet

import io.objectbox.Box

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class CitiesLoaderBox : BaseLoader<CityBox>() {

    fun execute(context: Context, resultSet: ResultSet, boxStore: Box<CityBox>, size: Int) {
        val cities = readFromFile(context, CITIES_CSV, size)

        logger.start()
        boxStore.put(cities)
        logger.logTime(resultSet.creating, INSERT_CITIES, size)

        logger.start()
        val citiesBox = boxStore.all
        logger.logTime(resultSet.reading, READ_CITIES, size)


        logger.start()
        for (i in citiesBox.indices) {
            citiesBox[i].name = i.toString()
        }
        boxStore.put(citiesBox)
        logger.logTime(resultSet.updating, UPDATE_CITIES, size)

        logger.start()
        boxStore.removeAll()
        logger.logTime(resultSet.deleting, DELETE_CITIES, size)
    }

    override fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): CityBox {
        return CityBox(id!!, name, latitude!!, longitude!!)
    }

    override fun createTimingLogger(): Timings {
        return Timings(TAG)
    }

    companion object {

        const val TAG = "box"
    }
}
