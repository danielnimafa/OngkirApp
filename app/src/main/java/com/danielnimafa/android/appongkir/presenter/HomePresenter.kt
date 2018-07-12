package com.danielnimafa.android.appongkir.presenter

import android.app.Activity
import com.danielnimafa.android.appongkir.presenter.interactor.HomeInteractor
import com.danielnimafa.android.appongkir.view.iface.HomeView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.danielnimafa.android.appongkir.model.response.Cities.Rajaongkir as citiesData
import com.danielnimafa.android.appongkir.model.response.Province.Rajaongkir as provinceData

class HomePresenter(val interactor: HomeInteractor) : MvpBasePresenter<HomeView>(),
        HomeInteractor.ProvinceDataListener,
        HomeInteractor.CitiesDataListener {

    lateinit var activity: Activity

    fun onCreate(activity: Activity) {
        this.activity = activity
        interactor.initiateInteractor()
    }

    fun onDestroy() {
        interactor.clearInteractor()
    }

    fun loadSourceData() {
        interactor.interactProvinceData(this)
    }

    override fun onSuccessProvince(rajaongkir: provinceData?) {
        interactor.interactCitiesData(this)
    }

    override fun onFailProvince(strFail: String?) {

    }

    override fun onErrorProvince(e: Exception) {

    }

    override fun onSuccessCities(rajaongkir: citiesData?) {

    }

    override fun onFailCities(strFail: String?) {

    }

    override fun onErrorCities(e: Exception) {

    }
}