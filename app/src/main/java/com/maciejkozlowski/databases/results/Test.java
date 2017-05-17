package com.maciejkozlowski.databases.results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciej on 2017-05-17.
 */

public class Test {

    public static List<Integer> SIZES = Arrays.asList(1000, 2000, 5000, 10_000, 20_000, 40_000, 60_000, 80_000, 100_000);

    private String typeOfDatabase;
    private int size;
    private List<Double> times = new ArrayList<>();

    public Test(String typeOfDatabase, int size) {
        this.typeOfDatabase = typeOfDatabase;
        this.size = size;
    }

    public String getLabel() {
        return size + "-" + typeOfDatabase;
    }

    public void addTest(double time) {
        times.add(time);
    }

    public List<Double> getTimes() {
        return times;
    }
}
