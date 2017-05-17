package com.maciejkozlowski.databases.results;

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao;
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox;
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm;
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciej on 2017-05-17.
 */

public class Test {

    public static List<Integer> SIZES = Arrays.asList(1000, 2000, 5000, 10_000, 20_000, 40_000, 60_000, 80_000, 100_000);
    public static List<String> TYPES = Arrays.asList(CitiesLoaderSql.TAG, CitiesLoaderDao.TAG, CitiesLoaderRealm.TAG, CitiesLoaderBox.TAG);

}
