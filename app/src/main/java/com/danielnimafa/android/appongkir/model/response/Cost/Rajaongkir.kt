package com.danielnimafa.android.appongkir.model.response.Cost


data class Rajaongkir(
    val query: QueryModel,
    val status: Status,
    val origin_details: OriginDetails,
    val destination_details: DestinationDetails,
    val results: List<Result>
)