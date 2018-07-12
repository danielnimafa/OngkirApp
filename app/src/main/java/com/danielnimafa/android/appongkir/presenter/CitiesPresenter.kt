package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.presenter.interactor.CitiesInteractor
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class CitiesPresenter(val interactor: CitiesInteractor) : MvpBasePresenter<DataListView>(),
        CitiesInteractor.OnCitiesLoadedListener {

    fun onCreate() {

    }

    fun onDestroyView() {

    }


}