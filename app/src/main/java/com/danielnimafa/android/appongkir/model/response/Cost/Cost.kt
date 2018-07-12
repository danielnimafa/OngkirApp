package com.danielnimafa.android.appongkir.model.response.Cost


data class Cost(
    val service: String,
    val description: String,
    val cost: List<Cost>
)