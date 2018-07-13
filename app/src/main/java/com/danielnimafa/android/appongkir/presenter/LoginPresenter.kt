package com.danielnimafa.android.appongkir.presenter

import com.danielnimafa.android.appongkir.presenter.interactor.LoginInteractor
import com.danielnimafa.android.appongkir.utils.extension.postDelayed
import com.danielnimafa.android.appongkir.view.iface.LoginView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

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

    fun postLoginSubmit(strUname: String) {
        view?.showProgress()
        postDelayed(1000) {
            interactor.submittingLogin(strUname, "password", this)
        }
    }

    override fun onSuccessLogin(name: String) {
        view?.run {
            hideProgress()
            showMessage("Selamat datang, ${name}", "", 22)
            gotoHome()
        }
    }

    override fun onFailedLogin(it: String?) {
        // prepare warning UI
        view?.hideProgress()
    }

    override fun onErrorLogin(it: Throwable) {
        // show the reason to the user why request has error
        view?.run {
            hideProgress()
            showMessage(it.message ?: "null", "failed", 11)
        }
    }
}