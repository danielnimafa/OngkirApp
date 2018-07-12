package com.danielnimafa.android.appongkir.presenter.interactor

import io.reactivex.disposables.CompositeDisposable

class HomeInteractor {

    interface OnFinishedCheckTerifListener {

    }

    lateinit var subs: CompositeDisposable

    fun initiateInteractor() {
        subs = CompositeDisposable()
    }

    fun clearInteractor() {
        subs.clear()
    }
}