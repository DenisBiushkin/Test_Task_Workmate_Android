package com.example.currencyconverter.presentation.currencies_feature.model

data class CurrencyUI(
    val currency: String,
    val name: String,
    val symbol:String,
    val amount: Double,
    val svgAssetPath: String,
    val showBalance: Boolean,
    val balance: Double
)
