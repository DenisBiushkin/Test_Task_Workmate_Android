package com.example.currencyconverter.presentation

import com.example.currencyconverter.domain.entity.Currency

data class CurrencyUI(
    val name: String,
    val flagUri:String,
    val symbol:String,
    val amount: Float,
    val showBalance:Boolean,
    val balance: Float
)
