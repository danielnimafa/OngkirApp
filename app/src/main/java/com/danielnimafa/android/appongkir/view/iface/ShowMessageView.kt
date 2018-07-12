package com.danielnimafa.android.appongkir.view.iface

/**
 * Created by danielnimafa on 31/01/18.
 */
interface ShowMessageView {
    fun showMessage(message: String, title: String? = "Failed", type: Int)
}