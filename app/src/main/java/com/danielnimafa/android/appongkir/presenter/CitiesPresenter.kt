package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.model.content.CityContent
import com.danielnimafa.android.appongkir.model.content.CityContentModel
import com.danielnimafa.android.appongkir.presenter.interactor.CitiesInteractor
import com.danielnimafa.android.appongkir.utils.Sout
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
        Sout.log("city data size", "${it.size}")
        val arrData: ArrayList<CityContentModel> = ArrayList()
        it.forEach {
            val itemData = CityContentModel(it.city_id, it.province_id, it.province, it.type,
                    it.city_name, it.postal_code)
            arrData.add(itemData)
        }
        view?.showList(arrData)
    }

    fun queryData(query: String, provinceID: String) {
        interactor.queryCitiesData(provinceID, query, this)
    }
}