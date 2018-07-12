package com.danielnimafa.android.appongkir.model.response.Cost

data class QueryModel(val origin: String,
                      val destination: String,
                      val weight: Int,
                      val courier: String)