package com.example.currencyconverter.presentation.exchange_feature.model

data class CurrencyExchange(
    val fromCurrency: String="1",
    val fromSymbol:String="#",
    val toCurrency: String="1",
    val toSymbol:String="#",
    val rate: String=""
)