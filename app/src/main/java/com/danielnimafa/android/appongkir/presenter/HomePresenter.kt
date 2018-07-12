package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.presenter.interactor.HomeInteractor
import com.danielnimafa.android.appongkir.view.iface.HomeView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class HomePresenter(val interactor: HomeInteractor) : MvpBasePresenter<HomeView>() {

    fun onCreate() {
        interactor.initiateInteractor()
    }

    fun onDestroy() {
        interactor.clearInteractor()
    }
}