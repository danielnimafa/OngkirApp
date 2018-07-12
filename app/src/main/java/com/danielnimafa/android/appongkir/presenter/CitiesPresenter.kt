package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.model.content.CityContent
import com.danielnimafa.android.appongkir.presenter.interactor.CitiesInteractor
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.realm.RealmResults

class CitiesPresenter(val interactor: CitiesInteractor) : MvpBasePresenter<DataListView>(),
        CitiesInteractor.OnCitiesLoadedListener {

    fun onCreate(provinceID: String) {
        interactor.initiateInteractor()
        interactor.loadCityData(this, provinceID)
    }

    fun onDestroyView() {
        interactor.clearInteractor()
    }

    override fun showCitiesData(it: RealmResults<CityContent>) {

    }
}