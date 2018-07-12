package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.presenter.interactor.ProvinceInteractor
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class ProvincePresenter(val interactor: ProvinceInteractor) : MvpBasePresenter<DataListView>() {

    fun onCreate() {
        interactor.initiateInteractor()
    }

    fun onDestroy() {
        interactor.clearInteractor()
    }

}