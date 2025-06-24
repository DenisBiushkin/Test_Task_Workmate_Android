package com.example.currencyconverter.data.dataSource.remote

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import kotlinx.coroutines.delay
import kotlin.random.Random

class RemoteRatesServiceImpl : RatesService {

    private val rates: Map<String, Double> = mapOf(
        "EUR" to 0.86033,
        "AUD" to 1.3906,
        "BGN" to 1.6826,
        "BRL" to 4.1225,
        "CAD" to 1.3196,
        "CHF" to 0.97003,
        "CNY" to 6.8354,
        "CZK" to 22.124,
        "DKK" to 6.4152,
        "GBP" to 0.77279,
        "HKD" to 7.8569,
        "HRK" to 6.3958,
        "HUF" to 280.89,
        "IDR" to 14904.0,
        "ILS" to 3.5881,
        "INR" to 72.025,
        "ISK" to 109.95,
        "JPY" to 111.45,
        "KRW" to 1122.5,
        "MXN" to 19.242,
        "MYR" to 4.1404,
        "NOK" to 8.4107,
        "NZD" to 1.5171,
        "PHP" to 53.85,
        "PLN" to 3.7152,
        "RON" to 3.9906,
        "RUB" to 68.461,
        "SEK" to 9.1115,
        "SGD" to 1.3765,
        "THB" to 32.805,
        "TRY" to 6.5628,
        "USD" to 1.0,
        "ZAR" to 15.334,
    )

    private suspend fun simulateNetworkDelayCoroutines() {
        val random = Random(System.currentTimeMillis())
        delay(random.nextInt(0, 1500).toLong())
    }

    override suspend fun getRates(
        baseCurrencyCode: String,
        amount: Double
    ): List<RateDto> {
        val random = Random(System.currentTimeMillis())
        val rate = requireNotNull(rates[baseCurrencyCode])
        simulateNetworkDelayCoroutines()
        return listOf(RateDto(baseCurrencyCode, amount)) +
                rates
                    .filter { it.key != baseCurrencyCode }
                    .map {
                        RateDto(
                            it.key,
                            it.value * amount * (1.0 + random.nextDouble() / 500.0 - 0.001) / rate
                        )
                    }
    }
}