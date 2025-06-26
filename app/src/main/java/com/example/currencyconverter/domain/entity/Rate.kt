package com.example.currencyconverter.domain.entity

data class Rate(
    val currencyCode: Currency,
    val rate: Double
)