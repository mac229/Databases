package com.maciejkozlowski.databases

import android.util.Log

import com.maciejkozlowski.databases.results.Result

/**
 * Created by Maciej Kozłowski on 04.05.17.
 */
class Timings(private val tag: String) {

    private var start = 0L

    fun start() {
        start = System.currentTimeMillis()
    }

    fun logTime(result: Result, label: String, size: Int) {
        val time = System.currentTimeMillis() - start
        //log("$label: $time")
        result.addTime("$size-$tag", time)
    }

    private fun log(label: String) {
        Log.d("###$tag", label)
    }
}
