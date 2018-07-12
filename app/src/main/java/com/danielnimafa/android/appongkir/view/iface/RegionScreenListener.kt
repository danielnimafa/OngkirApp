package com.danielnimafa.android.appongkir.view.iface

import com.danielnimafa.android.appongkir.model.content.ProvinceContentModel

interface RegionScreenListener {
    fun setupTitleScreen(title: String)
    fun gotoCityScreen(t: ProvinceContentModel)
    fun setupSubtitleScreen(subtitle: String)
}