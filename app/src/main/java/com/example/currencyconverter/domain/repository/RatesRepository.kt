package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate

interface RatesRepository{

    suspend fun getRates(baseCurrencyCode: Currency, amount: Double): List<Rate>

}