package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.entity.toCurrencyCode

fun RateDto.toDomain(): Rate {
    return Rate(
        //заменить
        currencyCode =currency.toCurrencyCode() ?: Currency.USD,
        rate = value
    )
}

fun Rate.toDto(): RateDto {
    return RateDto(
        currency = currencyCode.name,
        value = this.rate
    )
}