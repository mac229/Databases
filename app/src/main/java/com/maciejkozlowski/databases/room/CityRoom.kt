package com.maciejkozlowski.databases.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Maciej Kozłowski on 03.05.17.
 */
@Entity
data class CityRoom(
        @PrimaryKey var id: Long,
        @ColumnInfo(name = "name") var name: String?,
        @ColumnInfo(name = "latitude") var latitude: Double,
        @ColumnInfo(name = "longitude") var longitude: Double)

