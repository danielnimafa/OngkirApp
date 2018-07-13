package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.content.ProfilPengguna
import com.danielnimafa.android.appongkir.utils.PrefHelper
import com.danielnimafa.android.appongkir.utils.Sout
import io.realm.Realm

class SplashScreenActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_splash)
        realm = Realm.getDefaultInstance()
        PrefHelper.setDevMode(1)
        validateCredential()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun validateCredential() {
        realm.where(ProfilPengguna::class.java).findFirst()?.also {
            gotoHome()
        } ?: run {
            gotoLogin()
        }
    }

    private fun gotoHome() {
        startActivity(HomeActivity[this]); finish()
    }

    private fun gotoLogin() {
        startActivity(LoginActivity[this]); finish()
    }
}