package com.danielnimafa.android.appongkir.presenter

import android.app.Activity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.response.Cost.Rajaongkir
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
        HomeInteractor.CitiesDataListener,
        HomeInteractor.OnFinishedCheckTerifListener {

    companion object {
        val JNE = "jne"
        val TIKI = "tiki"
        val POS = "pos"
    }

    lateinit var activity: Activity
    var originID = ""
    var destinationID = ""
    var courierValue = ""
    var weightValue: Int = 0

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

    fun validateInput() {

        val message = activity.stringGet(R.string.message_fill_input_data)
        if (originID.isEmpty()) {
            view?.tos(message)
            return
        }

        if (destinationID.isEmpty()) {
            view?.tos(message)
            return
        }

        if (weightValue == 0) {
            view?.tos(message)
            return
        }

        view?.updateButtonMessage(activity.stringGet(R.string.str_wait))
        interactor.submitTarifData(courierValue, originID, destinationID, weightValue, this)
    }

    fun assignOriginCity(cityId: String) {
        originID = cityId
    }

    fun assignDestinationCity(cityId: String) {
        destinationID = cityId
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

    override fun onSuccessCost(t: Rajaongkir?) {
        view?.run {
            updateButtonMessage(activity.stringGet(R.string.str_cek_tarif))
            t?.also {
                val ongkirStr = Gson().toJson(it)
                gotoDetailScreen(ongkirStr)
            }
        }
    }

    override fun onFailCost(strFail: String?) {
        view?.run {
            updateButtonMessage(activity.stringGet(R.string.str_cek_tarif))
            val message = parseErrorBody(strFail)
            showPopupMessage("Failed", message)
        }
    }

    override fun onErrorCost(e: Exception) {
        val message = activity.getNetworkingError(e)
        view?.run {
            updateButtonMessage(activity.stringGet(R.string.str_cek_tarif))
            showPopupMessage("Failed", message)
        }
    }

    private fun parseErrorBody(str: String?): String {
        var message = ""
        Gson().fromJson(str, ErrorBodyModel::class.java)?.also {
            message = it.rajaongkir.status.description
        } ?: run {
            message = activity.stringGet(R.string.message_failed_fetching)
        }
        return message
    }

    fun handleOnfailSourceDataRequest(strFail: String?) {
        view?.run {
            hideProgress()
            val message = parseErrorBody(strFail)
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

    fun assignCourerValue(type: String) {
        courierValue = type
    }
}