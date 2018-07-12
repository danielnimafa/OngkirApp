package com.danielnimafa.android.appongkir.view.iface

import com.hannesdorfmann.mosby3.mvp.MvpView

interface HomeView : MvpView, ProgressView {
    fun showMessageScreen(message: String, showReloadButton: Boolean)
    fun showTarifLayout(state: Boolean)
}