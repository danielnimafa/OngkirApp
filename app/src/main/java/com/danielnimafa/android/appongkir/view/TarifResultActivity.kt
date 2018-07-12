package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.utils.Sout

class TarifResultActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, TarifResultActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_result_layout)
    }
}