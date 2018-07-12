package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.model.content.CityContent
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults

class CitiesInteractor {

    interface OnCitiesLoadedListener {
        fun showCitiesData(it: RealmResults<CityContent>)
    }

    lateinit var realm: Realm

    fun initiateInteractor() {
        realm = Realm.getDefaultInstance()
    }

    fun clearInteractor() {
        realm.close()
    }

    fun loadCityData(l: OnCitiesLoadedListener, provID: String) {
        realm.where(CityContent::class.java)
                .equalTo("province_id", provID)
                .findAll().also { l.showCitiesData(it) }
    }

    fun queryCitiesData(provID: String, query: String, l: OnCitiesLoadedListener) {
        if (query.isNotEmpty()) {
            realm.where(CityContent::class.java)
                    .equalTo("province_id", provID)
                    .contains("city_name", query, Case.INSENSITIVE)
                    .findAll().also { l.showCitiesData(it) }
        } else {
            loadCityData(l, provID)
        }
    }
}