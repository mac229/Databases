package com.maciejkozlowski.databases;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Maciej Kozłowski on 04.05.17.
 */
public abstract class BaseLoader<T> {

    public static final String DELETE_CITIES = "delete";
    public static final String INSERT_CITIES = "insert";
    public static final String UPDATE_CITIES = "update";
    public static final String READ_CITIES = "read";

    public static final int SIZE = 20_000;

    public static final String CITIES_CSV = "cities.csv";
    public static final String UPDATE_CITIES_CSV = "cities_update.csv";

    protected final Timings logger;
    protected Random random = new Random();


    public BaseLoader() {
        logger = createTimingLogger();
    }

    protected List<T> readFromFile(Context context, String file) {
        List<T> result = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(file)));

            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                Long id = Long.valueOf(strings[0]);
                Double latitude = Double.valueOf(strings[2]);
                Double longitude = Double.valueOf(strings[3]);
                String name = strings[1];
                T data = create(id, name, latitude, longitude);
                result.add(data);
                counter++;
                if (counter >= SIZE) {
                    break;
                }
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
