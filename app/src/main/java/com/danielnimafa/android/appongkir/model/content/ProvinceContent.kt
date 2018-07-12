package com.danielnimafa.android.appongkir.model.content

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class ProvinceContent : RealmModel {
    var province_id: String? = null
    var province: String? = null
}