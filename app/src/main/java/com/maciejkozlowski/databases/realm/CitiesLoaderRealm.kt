package com.maciejkozlowski.databases.realm


import android.content.Context
import com.maciejkozlowski.databases.BaseLoader
import com.maciejkozlowski.databases.Timings
import com.maciejkozlowski.databases.results.ResultSet
import io.realm.Realm

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class CitiesLoaderRealm : BaseLoader<CityRealm>() {

    fun execute(context: Context, resultSet: ResultSet, realm: Realm, size: Int) {
        val cities = readFromFile(context, CITIES_CSV, size)

        logger.start()
        realm.beginTransaction()
        realm.insert(cities)
        realm.commitTransaction()
        logger.logTime(resultSet.creating, BaseLoader.INSERT_CITIES, size)

        logger.start()
        val cityRealms = realm.where(CityRealm::class.java).findAll()
        logger.logTime(resultSet.reading, BaseLoader.READ_CITIES, size)

        logger.start()
        realm.beginTransaction()
        cityRealms.forEachIndexed { index, cityRealm ->
            cityRealm.name = index.toString()
        }
        realm.commitTransaction()
        logger.logTime(resultSet.updating, BaseLoader.UPDATE_CITIES, size)

        logger.start()
        realm.beginTransaction()
        realm.delete(CityRealm::class.java)
        realm.commitTransaction()
        logger.logTime(resultSet.deleting, BaseLoader.DELETE_CITIES, size)
    }

    override fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): CityRealm {
        return CityRealm(id!!, name, latitude!!, longitude!!)
    }

    override fun createTimingLogger(): Timings {
        return Timings(TAG)
    }

    companion object {
        const val TAG = "realm"
    }
}
