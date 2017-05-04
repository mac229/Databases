package com.maciejkozlowski.databases.greendao;

import android.content.Context;

import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * Created by Maciej Kozłowski on 02.05.17.
 */
public class DevOpenHelper extends DatabaseOpenHelper {

    public DevOpenHelper(Context context, String name) {
        super(context, name, 1);
    }
}
