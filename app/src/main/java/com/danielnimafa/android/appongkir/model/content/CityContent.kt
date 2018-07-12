package com.danielnimafa.android.appongkir.model.content

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class CityContent : RealmModel {
    var city_id: String? = null
    var province_id: String? = null
    var province: String? = null
    var type: String? = null
    var city_name: String? = null
    var postal_code: String? = null
}