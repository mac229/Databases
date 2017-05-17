package com.maciejkozlowski.databases.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2017-05-17.
 */

public class Result {

    private String type;
    private List<Test> tests = new ArrayList<>();

    public Result(String type) {
        this.type = type;
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public String getType() {
        return type;
    }

    public List<Test> getTests() {
        return tests;
    }
}
