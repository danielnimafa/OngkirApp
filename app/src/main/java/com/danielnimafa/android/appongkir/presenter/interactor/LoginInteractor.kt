package com.danielnimafa.android.appongkir.presenter.interactor

import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import okhttp3.ResponseBody

/*
* this class is layer for interacting with data source
*/

class LoginInteractor {

    interface OnFinishedLoginListener {
        fun onSuccessLogin(it: ResponseBody?)
        fun onFailedLogin(it: String?)
        fun onErrorLogin(it: Throwable)
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

    fun submittingLogin(deviceID: String, username: String, password: String, listener: OnFinishedLoginListener) {
        /*subs.add(ClientRequest.loginRequest("deviceId", "username", "password",
                success = { listener.onSuccessLogin(it) },
                fail = { listener.onFailedLogin(it) },
                error = { listener.onErrorLogin(it) }))*/
    }
}