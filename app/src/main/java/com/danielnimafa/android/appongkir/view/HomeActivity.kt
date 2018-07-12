package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.presenter.HomePresenter
import com.danielnimafa.android.appongkir.presenter.interactor.HomeInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.postDelayed
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.danielnimafa.android.appongkir.view.iface.HomeView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class HomeActivity : MvpActivity<HomeView, HomePresenter>(), HomeView {

    companion object {
        operator fun get(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun createPresenter() = HomePresenter(HomeInteractor())

    var backState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_home)
        presenter.onCreate()
        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onBackPressed() {

        if (backState) {
            super.onBackPressed()
            return
        }

        backState = true
        toast("Tekan sekali lagi untuk keluar")
        postDelayed(2000) { backState = false }
    }

    private fun setupView() {

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = stringGet(R.string.app_name)
        }


    }
}
