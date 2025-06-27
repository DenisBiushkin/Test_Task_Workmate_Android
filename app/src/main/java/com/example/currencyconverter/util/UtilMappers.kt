package com.example.currencyconverter.util

import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI


fun toCurrencyUI(rate: Rate,accountsMap:Map<String,Account>): CurrencyUI =rate.run{

    val strCurrency = currencyCode.name
    val pathSvg = CurrencyRepository.currencyLocaleMap[strCurrency]?:"ru"

    CurrencyUI(
        currency =strCurrency,
        name = CurrencyRepository.meta[strCurrency]?.name ?:"Unknown",
        symbol = CurrencyRepository.meta[strCurrency]?.symbol ?:"Unknown",
        svgAssetPath = "file:///android_asset/svg/$pathSvg.svg",
        balance = accountsMap[strCurrency]?.amount ?: 0.0,
        showBalance = accountsMap[strCurrency]!=null,
         amount = 1.0,
        isEditable = false
        )
}