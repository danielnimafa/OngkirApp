package com.danielnimafa.android.appongkir.model.response.Cities


data class Result(
    val city_id: String,
    val province_id: String,
    val province: String,
    val type: String,
    val city_name: String,
    val postal_code: String
)