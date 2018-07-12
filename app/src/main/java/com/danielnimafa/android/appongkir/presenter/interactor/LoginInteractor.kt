package com.danielnimafa.android.appongkir.presenter.interactor

import com.danielnimafa.android.appongkir.model.content.Userdata
import com.danielnimafa.android.appongkir.utils.Const
import com.danielnimafa.android.appongkir.utils.Sout
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import rx.Observable

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

    fun submittingLogin(username: String, password: String, listener: OnFinishedLoginListener) {
        Observable.just(realm).subscribe(
                {
                    realm.executeTransaction { r ->
                        r.createObject(Userdata::class.java).also {
                            it.username = username
                            it.name = "Anonymous"
                            it.avatar = "avatar"
                            it.token = Const.api_key
                        }
                    }
                },
                { Sout.trace(it as Exception); listener.onErrorLogin(it) },
                {
                    val name = realm.where(Userdata::class.java).findFirst()?.username
                    listener.onSuccessLogin(name ?: "user")
                })
    }
}