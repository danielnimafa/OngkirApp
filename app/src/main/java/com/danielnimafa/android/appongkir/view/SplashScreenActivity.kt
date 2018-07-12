package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.utils.PrefHelper
import com.danielnimafa.android.appongkir.utils.Sout

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_splash)
        PrefHelper.setDevMode(1)
        gotoHome()
    }

    private fun gotoHome() {
        startActivity(HomeActivity[this]); finish()
    }
}