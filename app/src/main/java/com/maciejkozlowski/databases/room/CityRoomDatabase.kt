package com.maciejkozlowski.databases.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Maciej Kozłowski on 28.12.2018.
 */
@Database(entities = [CityRoom::class], version = 1)
abstract class CityRoomDatabase : RoomDatabase() {

    abstract fun getCityRoomDao(): CityRoomDao
}