package com.example.currencyconverter.presentation.exchange_feature.model

import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI

data class ExchangeVMState(
    val rate: Rate = Rate(Currency.USD,1.0),
    val listTransaction: List<CurrencyUI> =emptyList(),
    val fromCurrency: Currency = Currency.USD,
    val toCurrency: Currency = Currency.RUB,
    val amount: Double = 0.0,
    val accessNavigate: Boolean = false,
    val isLoading: Boolean = true,
    val currencyExchange: CurrencyExchange = CurrencyExchange()
)



