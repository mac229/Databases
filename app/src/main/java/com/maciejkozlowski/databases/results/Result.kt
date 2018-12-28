package com.maciejkozlowski.databases.results

import java.util.*

/**
 * Created by Maciej on 2017-05-17.
 */

class Result(val type: String) {

    val times = HashMap<String, MutableList<Long>>()

    fun addTime(key: String, time: Long) {
        if (!times.containsKey(key)) {
            val timesList = ArrayList<Long>().apply {
                add(time)
            }
            times[key] = timesList
        } else {
            times[key]!!.add(time)
        }
    }
}
