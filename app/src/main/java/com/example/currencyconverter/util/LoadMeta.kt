package com.example.currencyconverter.util

import android.content.Context
import com.example.currencyconverter.domain.entity.CurrencyMeta
import kotlinx.serialization.json.Json

fun Context.loadCurrencyMeta(): Map<String, CurrencyMeta> {
    val json = assets.open("currencies.json")
        .bufferedReader().use { it.readText() }

    return Json.decodeFromString(json)
}