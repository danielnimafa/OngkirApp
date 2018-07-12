package com.danielnimafa.android.appongkir.utils

import java.text.DecimalFormat
import java.util.*

/**
 * Created by danielnimafa on 06/09/2016.
 */
class CurrencyManager(type: Int) {

    private val formatter: DecimalFormat
    private val rupiah = "Rp "
    private val idr = " IDR"

    init {
        val formatLargeNumber = "#,###,###,###,###"
        val formatSmallNumber = "#,###,###"
        formatter = DecimalFormat(if (type == 1) formatLargeNumber else formatSmallNumber)
    }

    fun formatPriceRP(price: String): String {
        var result = formatter.format(java.lang.Double.parseDouble(price))
        result = result.replace(",".toRegex(), ".")
        return rupiah + result
    }

    fun formatPriceRP(price: Double?): String {
        var result = formatter.format(price)
        result = result.replace(",".toRegex(), ".")
        return rupiah + result
    }

    fun formatPriceIDR(price: Double?): String {
        var result = formatter.format(price)
        result = result.replace(",".toRegex(), ".")
        return result + idr
    }

    fun inputFormatPrice(price: String): String {
        return formatter.format(Integer.parseInt(price).toLong())
    }

    fun inputFormatPrice(price: Int): String {
        return formatter.format(price.toLong())
    }

    companion object {

        fun formatRupiah(price: Double?): String {
            val formatter = DecimalFormat("#,###,###")
            /*String result = result.replaceAll(",", ".");*/
            val result = formatter.format(price)
            return "Rp " + result
        }

        fun formatIDR(price: Double?): String {
            val formatter = DecimalFormat("#,###,###")
            val result = formatter.format(price)
            return result + " IDR"
        }

        fun generateUniqueCode(min: Int, max: Int): Int {
            val rand = Random()
            return rand.nextInt(max - min + 1) + min
        }
    }
}
