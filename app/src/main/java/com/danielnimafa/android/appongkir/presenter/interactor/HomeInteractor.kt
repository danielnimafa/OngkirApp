package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.utils.Const
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.networking.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.danielnimafa.android.appongkir.model.response.Cities.Rajaongkir as citiesData
import com.danielnimafa.android.appongkir.model.response.Province.Rajaongkir as provinceData

class HomeInteractor {

    interface OnFinishedCheckTerifListener {

    }

    interface ProvinceDataListener {
        fun onSuccessProvince(rajaongkir: provinceData?)
        fun onFailProvince(strFail: String?)
        fun onErrorProvince(e: Exception)
    }

    interface CitiesDataListener {
        fun onSuccessCities(rajaongkir: citiesData?)
        fun onFailCities(strFail: String?)
        fun onErrorCities(e: Exception)
    }

    lateinit var subs: CompositeDisposable

    fun initiateInteractor() {
        subs = CompositeDisposable()
    }

    fun clearInteractor() {
        subs.clear()
    }

    fun interactProvinceData(l: ProvinceDataListener) {
        subs.add(ServiceGenerator.createService(Const.api_key).provinceData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) l.onSuccessProvince(it.body()?.rajaongkir) else l.onFailProvince(it.errorBody()?.string())
                }, {
                    Sout.trace(it as Exception)
                    l.onErrorProvince(it)
                }))
    }

    fun interactCitiesData(l: CitiesDataListener) {
        subs.add(ServiceGenerator.createService(Const.api_key).cityData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) l.onSuccessCities(it.body()?.rajaongkir) else l.onFailCities(it.errorBody()?.string())
                }, {
                    Sout.trace(it as Exception)
                    l.onErrorCities(it)
                }))
    }
}