package com.maciejkozlowski.databases;

import android.util.Log;

import com.maciejkozlowski.databases.results.Result;

/**
 * Created by Maciej Kozłowski on 04.05.17.
 */
public class Timings {

    private String tag;

    private long start;

    public Timings(String tag) {
        this.tag = tag;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void logTime(Result result, String label, int size) {
        long time = System.currentTimeMillis() - start;
        log(label + ": " + time);
        result.addTime(size + "-" + tag, time);
    }

    public void log(String label) {
        Log.d("###" + tag, label);
    }
}
