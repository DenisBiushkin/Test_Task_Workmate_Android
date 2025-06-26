package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.remote.RemoteRatesServiceImpl
import com.example.currencyconverter.data.mapper.toDomain
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.repository.RatesRepository

class RatesRepositoryImpl: RatesRepository {
    override suspend fun getRates(baseCurrencyCode: Currency, amount: Double): List<Rate> {
        return RemoteRatesServiceImpl().getRates(
            baseCurrencyCode.name,
            amount
        ).map { it.toDomain() }
    }
}