package com.maciejkozlowski.databases.realm


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Maciej Kozłowski on 02.05.17.
 */
open class CityRealm : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var name: String? = null
    var latitude: Double = 0.toDouble()
    var longitude: Double = 0.toDouble()

    constructor()

    constructor(id: Long, name: String, latitude: Double, longitude: Double) {
        this.id = id
        this.name = name
        this.latitude = latitude
        this.longitude = longitude
    }
}
