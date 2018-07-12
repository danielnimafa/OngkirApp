package com.danielnimafa.android.appongkir.model.response.Cost


data class Result(
    val code: String,
    val name: String,
    val costs: List<Cost>
)