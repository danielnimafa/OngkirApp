package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.model.content.ProfilPengguna
import com.danielnimafa.android.appongkir.utils.Const
import com.danielnimafa.android.appongkir.utils.Sout
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm

/*
* this class is layer for interacting with data source
*/

class LoginInteractor {

    interface OnFinishedLoginListener {
        fun onSuccessLogin(s: String)
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

    fun submittingLogin(uname: String, password: String, listener: OnFinishedLoginListener) {
        Sout.log("login", "executed")
        realm.executeTransactionAsync({ r ->
            r.createObject(ProfilPengguna::class.java).apply {
                username = uname
                name = "Anonymous"
                avatar = "avatar"
                token = Const.api_key
            }
        }, {
            Sout.log("login", "success")
            realm.where(ProfilPengguna::class.java).findFirst()?.also {
                listener.onSuccessLogin(it.username ?: "username")
            }
        }, { Sout.trace(it as Exception); listener.onErrorLogin(it) })
    }
}