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

    public void log(String label) {
        long time = System.currentTimeMillis() - start;
        Log.d(tag, label + ": " + time);
    }
}
