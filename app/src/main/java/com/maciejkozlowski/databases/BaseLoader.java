package com.maciejkozlowski.databases;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Kozłowski on 04.05.17.
 */
public abstract class BaseLoader<T> {

    public static final String INSERT_CITIES = "insert cities";

    protected final Timings timingLogger;

    public BaseLoader() {
        timingLogger = createTimingLogger();
    }

    protected void measureInsert() {

    }

    protected List<T> read(Context context) {
        List<T> result = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("cities.csv")));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                Long id = Long.valueOf(strings[0]);
                Double latitude = Double.valueOf(strings[2]);
                Double longitude = Double.valueOf(strings[3]);
                String name = strings[1];
                T data = create(id, name, latitude, longitude);
                result.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    protected abstract T create(Long id, String name, Double latitude, Double longitude);

    protected abstract Timings createTimingLogger();
}
