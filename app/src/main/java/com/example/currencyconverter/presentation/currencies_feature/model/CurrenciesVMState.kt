package com.example.currencyconverter.presentation.currencies_feature.model

import com.example.currencyconverter.domain.entity.Currency

data class CurrenciesVMState(
    val currencies: List<CurrencyUI> = emptyList(),
    val selectedCurrency: Currency = Currency.USD,
    val fromSelectedCurrency: String = "",
    val valueCurrency: Double = 1.0,
    val contentState: ContentState = ContentState.List,
    val accessNavigate: Boolean = false
)


