package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.repository.RatesRepository

class GetRatesUseCase(
    private val ratesRepository: RatesRepository
) {

    suspend fun invokes(baseCurrencyCode: Currency, amount: Double): List<Rate>{
        return ratesRepository.getRates(baseCurrencyCode, amount)
    }
}