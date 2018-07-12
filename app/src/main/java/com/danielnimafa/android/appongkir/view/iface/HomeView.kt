package com.danielnimafa.android.appongkir.view.iface

import com.hannesdorfmann.mosby3.mvp.MvpView

interface HomeView : MvpView, ProgressView {
    fun showMessageScreen(message: String, showReloadButton: Boolean)
    fun showTarifLayout(state: Boolean)
    fun tos(message: String)
    fun updateButtonMessage(message: String)
    fun showPopupMessage(title: String, message: String)
    fun gotoDetailScreen(str: String)
}