package com.maciejkozlowski.databases.greendao

import android.content.Context

import org.greenrobot.greendao.database.DatabaseOpenHelper

/**
 * Created by Maciej Kozłowski on 02.05.17.
 */
class DevOpenHelper(context: Context) : DatabaseOpenHelper(context, DATABASE_NAME, 1) {

    companion object {
        private const val DATABASE_NAME = "cities-dao.db"
    }
}
