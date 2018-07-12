package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.model.content.ProvinceContent
import com.danielnimafa.android.appongkir.model.content.ProvinceContentModel
import com.danielnimafa.android.appongkir.presenter.interactor.ProvinceInteractor
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.realm.RealmResults

class ProvincePresenter(val interactor: ProvinceInteractor) : MvpBasePresenter<DataListView>(),
        ProvinceInteractor.ProvinceDataListener {

    fun onCreate() {
        interactor.initiateInteractor()
        interactor.loadProvinceData(this)
    }

    fun onDestroy() {
        interactor.clearInteractor()
    }

    override fun showProvinceData(result: RealmResults<ProvinceContent>) {
        val arrData: ArrayList<ProvinceContentModel> = ArrayList()
        result.forEach {
            val itemData = ProvinceContentModel(it.province_id, it.province)
            arrData.add(itemData)
        }
        view?.showList(arrData)
    }
}