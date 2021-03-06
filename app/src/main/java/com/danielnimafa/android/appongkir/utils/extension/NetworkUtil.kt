package com.danielnimafa.android.appongkir.utils.extension

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.danielnimafa.android.appongkir.R
import okhttp3.MediaType
import okhttp3.RequestBody
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/*
 * Created by danielnimafa on 02/27/18.
 */

fun Activity.isOnline(online: () -> Unit, offline: () -> Unit) {
    if (checkOnlineStatus()) online() else offline()
}

fun Activity.checkOnlineStatus(): Boolean {
    val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connManager.activeNetworkInfo
    return (networkInfo != null && networkInfo.isConnected)
}

fun Activity.getNetworkingError(e: Throwable): String {

    val msgError: String
    if (e is SocketTimeoutException) {
        msgError = stringGet(R.string.timeout_msg)
    } else if (e is SocketException || e is UnknownHostException) {
        msgError = stringGet(R.string.server_lost_conn)
    } else if (e is NullPointerException) {
        msgError = stringGet(R.string.invalid_response)
    } else {
        msgError = e.message ?: "null"
    }

    return msgError
}

fun populateErrorMessages(errors: List<String>?): String {
    val sb = StringBuilder()
    errors?.run {
        val count = this.size
        if (count > 0) this.forEachIndexed { index, s ->
            sb.append("• $s" + if (index == count - 1) "" else "\n")
        }
    }
    return sb.toString()
}

fun createRequestBody(key: String, value: String?): RequestBody {
    return RequestBody.create(MediaType.parse(key), value)
}