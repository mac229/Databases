package com.maciejkozlowski.databases.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

/**
 * Created by Maciej Kozłowski on 12.12.16.
 */
class CitiesDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Queries.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    fun get(): List<CitySql> {
        val cities = ArrayList<CitySql>()

        val db = readableDatabase
        val cursor = db.query(Queries.TABLE_NAME, null, null, null, null, null, null)
        cursor.moveToPosition(-1)
        while (cursor.moveToNext()) {
            val city = CitySql(cursor.getInt(0).toLong(), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3))
            cities.add(city)
        }

        cursor.close()
        db.close()

        return cities
    }

    operator fun get(cityId: String): CitySql? {
        val selection = Queries.NAME + " = ?"
        val args = arrayOf(cityId)

        val db = readableDatabase
        val cursor = db.query(Queries.TABLE_NAME, null, selection, args, null, null, null)

        var CitySql: CitySql? = null
        if (cursor.moveToFirst()) {
            CitySql = CitySql(cursor.getInt(0).toLong(), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3))
        }

        cursor.close()
        db.close()

        return CitySql
    }

    fun set(cities: List<CitySql>) {
        val db = writableDatabase
        db.beginTransaction()

        for (city in cities) {
            val values = ContentValues()
            values.put(Queries.ID, city.id)
            values.put(Queries.NAME, city.name)
            values.put(Queries.LATITUDE, city.latitude)
            values.put(Queries.LONGITUDE, city.longitude)

            db.insert(Queries.TABLE_NAME, null, values)
        }

        db.setTransactionSuccessful()
        db.endTransaction()
        db.close()
    }

    fun update(cities: List<CitySql>) {
        val db = writableDatabase
        db.beginTransaction()

        for (city in cities) {
            val values = ContentValues()
            values.put(Queries.ID, city.id)
            values.put(Queries.NAME, city.name)
            values.put(Queries.LATITUDE, city.latitude)
            values.put(Queries.LONGITUDE, city.longitude)

            db.update(Queries.TABLE_NAME, values, Queries.ID + "=" + city.id, null)
        }

        db.setTransactionSuccessful()
        db.endTransaction()
        db.close()
    }

    fun dropAndCreate() {
        val db = writableDatabase
        db.execSQL(Queries.SQL_DELETE_ENTRIES)
        db.execSQL(Queries.SQL_CREATE_ENTRIES)
        db.close()
    }

    private object Queries {

        const val TABLE_NAME = "CITY"

        const val ID = "ID"
        const val NAME = "NAME"
        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"

        const val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," +
                LATITUDE + " REAL," +
                LONGITUDE + " REAL" + ")"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

    companion object {

        private const val DATABASE_NAME = "cities-sqlite.db"
        private const val DATABASE_VERSION = 1
    }
}
