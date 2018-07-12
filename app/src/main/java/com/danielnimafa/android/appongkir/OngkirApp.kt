package com.danielnimafa.android.appongkir

import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.danielnimafa.android.appongkir.utils.extension.DelegatesExt
import com.danielnimafa.android.appongkir.utils.realmdb.DBMigration
import com.pixplicity.easyprefs.library.Prefs
import io.realm.Realm
import io.realm.RealmConfiguration

class OngkirApp : MultiDexApplication() {

    companion object {
        var INSTANCE: OngkirApp by DelegatesExt.notNullSingleValue()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("ongkirapp.realm")
                .schemaVersion(0)
                .migration(DBMigration())
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        Prefs.Builder()
                .setContext(INSTANCE)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}