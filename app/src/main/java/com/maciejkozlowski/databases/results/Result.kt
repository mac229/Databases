package com.maciejkozlowski.databases.results

import java.util.*

/**
 * Created by Maciej on 2017-05-17.
 */

class Result(val type: String) {

    val times = HashMap<String, MutableList<Long>>()

    fun addTime(key: String, time: Long) {
        if (times.containsKey(key)) {
            times[key]!!.add(time)
        } else {
            times[key] = ArrayList<Long>().apply {
                add(time)
            }
        }
    }
}
