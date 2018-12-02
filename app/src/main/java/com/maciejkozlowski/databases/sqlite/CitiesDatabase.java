package com.maciejkozlowski.databases.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Maciej Kozłowski on 12.12.16.
 */
public class CitiesDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cities-sqlite.db";
    private static final int DATABASE_VERSION = 1;

    public CitiesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Queries.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<CitySql> get() {
        List<CitySql> cities = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Queries.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            CitySql city = new CitySql(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
            cities.add(city);
        }

        cursor.close();
        db.close();

        return cities;
    }

    @Nullable
    public CitySql get(String cityId) {
        String selection = Queries.NAME + " = ?";
        String[] args = {cityId};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Queries.TABLE_NAME, null, selection, args, null, null, null);

        CitySql CitySql = null;
        if (cursor.moveToFirst()) {
            CitySql = new CitySql(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
        }

        cursor.close();
        db.close();

        return CitySql;
    }

    public void set(List<CitySql> cities) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        for (CitySql city : cities) {
            ContentValues values = new ContentValues();
            values.put(Queries.ID, city.getId());
            values.put(Queries.NAME, city.getName());
            values.put(Queries.LATITUDE, city.getLatitude());
            values.put(Queries.LONGITUDE, city.getLongitude());

            db.insert(Queries.TABLE_NAME, null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void update(List<CitySql> cities) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        for (CitySql city : cities) {
            ContentValues values = new ContentValues();
            values.put(Queries.ID, city.getId());
            values.put(Queries.NAME, city.getName());
            values.put(Queries.LATITUDE, city.getLatitude());
            values.put(Queries.LONGITUDE, city.getLongitude());

            db.update(Queries.TABLE_NAME, values, Queries.ID + "=" + city.getId(), null);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void dropAndCreate() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(Queries.SQL_DELETE_ENTRIES);
        db.execSQL(Queries.SQL_CREATE_ENTRIES);
        db.close();
    }

    private static class Queries {

        private static final String TABLE_NAME = "CITY";

        private static final String ID = "ID";
        private static final String NAME = "NAME";
        private static final String LATITUDE = "LATITUDE";
        private static final String LONGITUDE = "LONGITUDE";


        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY," +
                        NAME + " TEXT," +
                        LATITUDE + " REAL," +
                        LONGITUDE + " REAL" + ")";

        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
