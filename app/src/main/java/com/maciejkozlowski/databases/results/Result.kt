package com.maciejkozlowski.databases.results

import java.util.*

/**
 * Created by Maciej on 2017-05-17.
 */

class Result(val type: String) {

    private val times = HashMap<String, MutableList<Long>>()

    fun addTime(key: String, time: Long) {
        if (!times.containsKey(key)) {
            val timesList = object : ArrayList<Long>() {
                init {
                    add(time)
                }
            }
            times[key] = timesList
        } else {
            times[key]!!.add(time)
        }
    }

    fun getTimes(): Map<String, List<Long>> {
        return times
    }
}
