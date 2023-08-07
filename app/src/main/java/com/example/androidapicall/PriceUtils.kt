package com.example.androidapicall

import java.text.NumberFormat
import java.util.Locale

object PriceUtils {
    fun formattedPrice(price: Int): String {
        val formatted = NumberFormat.getCurrencyInstance(Locale.US).format(price.toLong())
        return formatted.replace("$", "$ ")
    }
}
