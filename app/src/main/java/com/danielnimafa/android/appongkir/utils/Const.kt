package com.danielnimafa.android.appongkir.utils

import android.Manifest
import android.content.pm.PackageManager

/**
 * Created by danielnimafa on 02/27/18.
 */

object Const {

    const val app_name = "Ongkir App" // replace with your own app name

    const val ROOT_URL = "root_url"
    const val endpoint_test = "api.rajaongkir.com/starter"
    const val endpoint = "api.rajaongkir.com/starter"
    const val BASE_URL_TEST = "http://$endpoint_test/"
    const val BASE_URL = "http://$endpoint/"
    const val IS_LOGIN = "islogin"
    const val LOGIN = "user/login"
    const val LOGOUT = "user/logout"

    const val header_appkey = "header_appkey"
    const val header_appsecret = "header_appsecret"
    const val header_deviceID = "header_deviceID"
    const val header_client = "header_client"
    const val header_vendor = "header_vendor"
    const val header_auth = "Authorization"
    const val value_header_appkey = "value_header_appkey"
    const val value_header_appsecret = "value_header_appsecret"

    const val CONTENT_LIMIT = 10

    const val LOCATION_REQ_CODE = 17
    const val PERMISSION_LOCATION_REQ_CODE = 27
    const val cameraPermRequestCode = 199
    const val CAMERA_REQUEST_CODE = 10
    const val STORAGE_REQUEST_CODE = 11
    const val REQUEST_READ_PHONE_STATE = 9

    const val multipartFormdata = "multipart/form-data"
    const val IMAGE_DIRECTORY_NAME = "Picture"
    const val NO_MEDIA_FILENAME = ".nomedia"
    const val IMAGECACHE_DIRECTORY_NAME = "CACHE"
    const val granted = PackageManager.PERMISSION_GRANTED
    const val denied = PackageManager.PERMISSION_DENIED

    val PERMISSIONS_LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)

    const val INTENT_FILTER_LOCATION = "intent-filter-detect-location"
    const val INTENT_FILTER_INTERNET_CONNECTION = "intent-filter-internet-connection"
}