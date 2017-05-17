package com.maciejkozlowski.databases;

import android.app.Application;

import com.maciejkozlowski.databases.greendao.DaoMaster;
import com.maciejkozlowski.databases.greendao.DaoSession;
import com.maciejkozlowski.databases.greendao.DevOpenHelper;
import com.maciejkozlowski.databases.objectbox.MyObjectBox;

import org.greenrobot.greendao.database.Database;

import io.objectbox.BoxStore;
import io.realm.Realm;

/**
 * Created by Maciej Kozłowski on 01.05.17.
 */
public class MyApplication extends Application {

    private BoxStore boxStore;
    private DaoSession daoSession;
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();

        DevOpenHelper helper = new DevOpenHelper(this);
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        DaoMaster.createAllTables(daoMaster.getDatabase(), true);

        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public Realm getRealm() {
        return realm;
    }
}
