package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.model.content.CityContent
import com.danielnimafa.android.appongkir.model.content.ProvinceContent
import com.danielnimafa.android.appongkir.model.content.Userdata
import com.danielnimafa.android.appongkir.utils.Const
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.createRequestBody
import com.danielnimafa.android.appongkir.utils.networking.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import rx.Observable
import com.danielnimafa.android.appongkir.model.response.Cities.Rajaongkir as citiesData
import com.danielnimafa.android.appongkir.model.response.Cities.Result as cities
import com.danielnimafa.android.appongkir.model.response.Cost.Rajaongkir as costData
import com.danielnimafa.android.appongkir.model.response.Province.Rajaongkir as provinceData
import com.danielnimafa.android.appongkir.model.response.Province.Result as provincies

class HomeInteractor {

    interface OnFinishedCheckTerifListener {
        fun onSuccessCost(rajaongkir: costData?)
        fun onFailCost(string: String?)
        fun onErrorCost(e: Exception)
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

    interface UserListener {
        fun onSuccessLogout()
    }

    lateinit var subs: CompositeDisposable
    lateinit var realm: Realm

    fun initiateInteractor() {
        subs = CompositeDisposable()
        realm = Realm.getDefaultInstance()
    }

    fun clearInteractor() {
        subs.clear()
        realm.close()
    }

    fun interactProvinceData(l: ProvinceDataListener) {
        subs.add(ServiceGenerator.createService(Const.api_key).provinceData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) l.onSuccessProvince(it.body()?.rajaongkir)
                    else l.onFailProvince(it.errorBody()?.string())
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
                    if (it.isSuccessful) l.onSuccessCities(it.body()?.rajaongkir)
                    else l.onFailCities(it.errorBody()?.string())
                }, {
                    Sout.trace(it as Exception)
                    l.onErrorCities(it)
                }))
    }

    fun saveProvinceData(results: List<provincies>) {
        realm.executeTransaction { r ->
            results.forEach {
                r.createObject(ProvinceContent::class.java).apply {
                    province_id = it.province_id
                    province = it.province
                }
            }
        }
    }

    fun saveCitiesData(results: List<cities>) {
        realm.executeTransaction { r ->
            results.forEach {
                r.createObject(CityContent::class.java).apply {
                    city_id = it.city_id
                    city_name = it.city_name
                    province_id = it.province_id
                    province = it.province
                    type = it.type
                    postal_code = it.postal_code
                }
            }
        }
    }

    fun clearExistingSourceData() {
        realm.executeTransaction {
            it.run {
                where(ProvinceContent::class.java).findAll().apply { deleteAll() }
                where(CityContent::class.java).findAll().apply { deleteAll() }
            }
        }
    }

    fun submitTarifData(courier: String, originID: String, destinationID: String, weightValue: Int, l: OnFinishedCheckTerifListener) {

        val courierRb = createRequestBody(Const.multipartFormdata, courier)
        val originRb = createRequestBody(Const.multipartFormdata, originID)
        val destinationRb = createRequestBody(Const.multipartFormdata, destinationID)
        val weightRb = createRequestBody(Const.multipartFormdata, "$weightValue")

        subs.add(ServiceGenerator.createService(Const.api_key)
                .costData(originRb, destinationRb, weightRb, courierRb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) l.onSuccessCost(it.body()?.rajaongkir)
                    else l.onFailCost(it.errorBody()?.string())
                }, {
                    Sout.trace(it as Exception)
                    l.onErrorCost(it)
                }))
    }

    fun loggingOutUser(l: UserListener) {
        realm.executeTransaction { r ->
            r.where(Userdata::class.java).findAll().apply { deleteAllFromRealm() }
        }
        l.onSuccessLogout()
    }
}