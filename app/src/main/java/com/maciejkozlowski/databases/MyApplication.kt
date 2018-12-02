package com.maciejkozlowski.databases

import android.app.Application
import com.maciejkozlowski.databases.greendao.DaoMaster
import com.maciejkozlowski.databases.greendao.DaoSession
import com.maciejkozlowski.databases.greendao.DevOpenHelper
import com.maciejkozlowski.databases.objectbox.MyObjectBox
import io.objectbox.BoxStore
import io.realm.Realm

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
class MyApplication : Application() {

    lateinit var boxStore: BoxStore private set

    lateinit var daoSession: DaoSession private set

    lateinit var realm: Realm private set

    override fun onCreate() {
        super.onCreate()
        boxStore = MyObjectBox.builder().androidContext(this).build()

        val helper = DevOpenHelper(this)
        val db = helper.writableDb
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
        DaoMaster.createAllTables(daoMaster.database, true)

        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }
}
