package com.danielnimafa.android.appongkir.model.content

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Userdata : RealmModel {
    var name: String = ""
    var username: String = ""
    var avatar: String = ""
    var token: String = ""
}