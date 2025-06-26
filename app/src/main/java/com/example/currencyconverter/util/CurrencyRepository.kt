package com.example.currencyconverter.util

import com.example.currencyconverter.domain.entity.CurrencyMeta

object CurrencyRepository {
    lateinit var meta: Map<String, CurrencyMeta>

    val currencyLocaleMap = mapOf(
        "USD" to "us",
        "GBP" to "gb",
        "EUR" to "eu",
        "AUD" to "au",
        "BGN" to "bg",
        "BRL" to "br",
        "CAD" to "ca",
        "CHF" to "ch",
        "CNY" to "cn",
        "CZK" to "cz",
        "DKK" to "dk",
        "HKD" to "hk",
        "HRK" to "hr",
        "HUF" to "hu",
        "IDR" to "id",
        "ILS" to "il",
        "INR" to "in",
        "ISK" to "is",
        "JPY" to "jp",
        "KRW" to "kr",
        "MXN" to "mx",
        "MYR" to "my",
        "NOK" to "no",
        "NZD" to "nz",
        "PHP" to "ph",
        "PLN" to "pl",
        "RON" to "ro",
        "RUB" to "ru",
        "SEK" to "se",
        "SGD" to "sg",
        "THB" to "th",
        "TRY" to "tr",
        "ZAR" to "za"
    )
}