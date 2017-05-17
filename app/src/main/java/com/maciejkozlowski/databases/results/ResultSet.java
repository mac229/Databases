package com.maciejkozlowski.databases.results;

/**
 * Created by Maciej on 2017-05-17.
 */

public class ResultSet {

    private Result creating = new Result("creating");
    private Result reading = new Result("reading");
    private Result deleting = new Result("deleting");
    private Result updating = new Result("updating");

    public Result getCreating() {
        return creating;
    }

    public Result getReading() {
        return reading;
    }

    public Result getDeleting() {
        return deleting;
    }

    public Result getUpdating() {
        return updating;
    }
}
