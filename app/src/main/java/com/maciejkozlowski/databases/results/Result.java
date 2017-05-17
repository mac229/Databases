package com.maciejkozlowski.databases.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maciej on 2017-05-17.
 */

public class Result {

    private String type;
    private Map<String, List<Long>> times = new HashMap<>();

    public Result(String type) {
        this.type = type;
    }

    public void addTime(String key, final long time) {
        if (!times.containsKey(key)) {
            List<Long> timesList = new ArrayList<Long>() {{
                add(time);
            }};
            times.put(key, timesList);
        } else {
            times.get(key).add(time);
        }
    }

    public String getType() {
        return type;
    }

    public Map<String, List<Long>> getTimes() {
        return times;
    }
}
