package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.model.content.ProvinceContent
import io.realm.Realm
import io.realm.RealmResults

class ProvinceInteractor {

    interface ProvinceDataListener {
        fun showProvinceData(result: RealmResults<ProvinceContent>)
    }

    lateinit var realm: Realm

    fun initiateInteractor() {
        realm = Realm.getDefaultInstance()
    }

    fun clearInteractor() {
        realm.close()
    }

    fun loadProvinceData(l: ProvinceDataListener) {
        realm.where(ProvinceContent::class.java).findAll().also { l.showProvinceData(it) }
    }
}