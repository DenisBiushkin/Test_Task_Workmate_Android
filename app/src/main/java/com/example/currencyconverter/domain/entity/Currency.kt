package com.example.currencyconverter.domain.entity

enum class Currency {
    USD,
    GBP,
    EUR,
    AUD,
    BGN,
    BRL,
    CAD,
    CHF,
    CNY,
    CZK,
    DKK,
    HKD,
    HRK,
    HUF,
    IDR,
    ILS,
    INR,
    ISK,
    JPY,
    KRW,
    MXN,
    MYR,
    NOK,
    NZD,
    PHP,
    PLN,
    RON,
    RUB,
    SEK,
    SGD,
    THB,
    TRY,
    ZAR,
}
fun String.toCurrencyCode(): Currency? =
    Currency.entries.find { it.name == this }

fun Currency.toRaw(): String = this.name