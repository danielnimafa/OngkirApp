package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.presenter.interactor.LoginInteractor
import com.danielnimafa.android.appongkir.view.iface.LoginView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import okhttp3.ResponseBody

/*
* this class is layer for presenting data and handling logic flow
*/

class LoginPresenter(private val interactor: LoginInteractor) : MvpBasePresenter<LoginView>(),
        LoginInteractor.OnFinishedLoginListener {


    fun onCreate() {
        interactor.initiateInteractor()
    }

    fun onDestroy() {
        interactor.clearInteractor()
    }

    fun postLoginSubmit() {
        view?.showProgress()
        interactor.submittingLogin("deviceId", "username", "password", this)
    }

    override fun onSuccessLogin(it: ResponseBody?) {
        // do on success scenario
        view?.hideProgress()
    }

    override fun onFailedLogin(it: String?) {
        // prepare warning UI
        view?.hideProgress()
    }

    override fun onErrorLogin(it: Throwable) {
        // show the reason to the user why request has error
        view?.hideProgress()
    }
}