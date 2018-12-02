package com.maciejkozlowski.databases

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

/**
 * Created by Maciej Kozłowski on 04.05.17.
 */
abstract class BaseLoader<T> {

    protected val logger = createTimingLogger()

    protected fun readFromFile(context: Context, file: String, size: Int): List<T> {
        val result = ArrayList<T>()
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(context.assets.open(file)))

            var line = reader.readLine()
            var counter = 0
            while (line != null) {
                val strings = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val id = java.lang.Long.valueOf(strings[0])
                val latitude = java.lang.Double.valueOf(strings[2])
                val longitude = java.lang.Double.valueOf(strings[3])
                val name = strings[1]
                val data = create(id, name, latitude, longitude)
                result.add(data)
                counter++
                if (counter >= size) {
                    break
                }
                line = reader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return result
    }

    protected abstract fun create(id: Long?, name: String, latitude: Double?, longitude: Double?): T

    protected abstract fun createTimingLogger(): Timings

    companion object {

        val DELETE_CITIES = "delete"
        val INSERT_CITIES = "insert"
        val UPDATE_CITIES = "update"
        val READ_CITIES = "read"

        val CITIES_CSV = "cities.csv"
    }
}
