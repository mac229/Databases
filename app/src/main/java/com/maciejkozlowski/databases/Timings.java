package com.maciejkozlowski.databases;

import android.util.Log;

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

    public void logTime(String label) {
        long time = System.currentTimeMillis() - start;
        log(label + ": " + time);
    }

    public void log(String label) {
        Log.d(tag, label);
    }
}
