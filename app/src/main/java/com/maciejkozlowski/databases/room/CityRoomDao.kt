package com.maciejkozlowski.databases.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Created by Maciej Kozłowski on 03.05.17.
 */
@Dao
interface CityRoomDao {

    @Query("SELECT * FROM CityRoom")
    fun getAll(): List<CityRoom>

    @Insert
    fun insertAll(users: List<CityRoom>)

    @Update
    fun update(users: List<CityRoom>)

    @Query("DELETE FROM cityRoom")
    fun deleteAll()
}