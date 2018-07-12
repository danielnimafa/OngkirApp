package com.danielnimafa.android.appongkir.presenter

import android.app.Activity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.response.ErrorBody.ErrorBodyModel
import com.danielnimafa.android.appongkir.presenter.interactor.HomeInteractor
import com.danielnimafa.android.appongkir.utils.extension.getNetworkingError
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.danielnimafa.android.appongkir.view.iface.HomeView
import com.google.gson.Gson
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
        view?.showProgress()
        interactor.clearExistingSourceData()
        interactor.interactProvinceData(this)
    }

    override fun onSuccessProvince(rajaongkir: provinceData?) {
        rajaongkir?.also { interactor.saveProvinceData(it.results) }
        interactor.interactCitiesData(this)
    }

    override fun onFailProvince(strFail: String?) {
        handleOnfailSourceDataRequest(strFail)
    }

    override fun onErrorProvince(e: Exception) {
        handleOnErrorSourceDataRequest(e)
    }

    override fun onSuccessCities(rajaongkir: citiesData?) {
        view?.run {
            rajaongkir?.also { interactor.saveCitiesData(it.results) }
            hideProgress()
            showTarifLayout(true)
        }
    }

    override fun onFailCities(strFail: String?) {
        handleOnfailSourceDataRequest(strFail)
    }

    override fun onErrorCities(e: Exception) {
        handleOnErrorSourceDataRequest(e)
    }

    fun handleOnfailSourceDataRequest(strFail: String?) {
        view?.run {
            hideProgress()
            var message = ""
            Gson().fromJson(strFail, ErrorBodyModel::class.java)?.also {
                message = it.rajaongkir.status.description
            } ?: run {
                message = activity.stringGet(R.string.message_failed_fetching)
            }
            showMessageScreen(message, true)
            showTarifLayout(false)
        }
    }

    fun handleOnErrorSourceDataRequest(e: Exception) {
        view?.run {
            hideProgress()
            val message = activity.getNetworkingError(e)
            showMessageScreen(message, true)
            showTarifLayout(false)
        }
    }
}