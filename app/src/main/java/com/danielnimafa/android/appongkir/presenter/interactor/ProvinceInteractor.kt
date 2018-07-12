package com.danielnimafa.android.appongkir.presenter.interactor

import io.realm.Realm

class ProvinceInteractor {

    interface ProvinceDataListener {

    }

    lateinit var realm: Realm

    fun initiateInteractor() {
        realm = Realm.getDefaultInstance()
    }

    fun clearInteractor() {
        realm.close()
    }
}