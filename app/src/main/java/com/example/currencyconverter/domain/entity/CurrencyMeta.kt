package com.example.currencyconverter.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyMeta(
    val name: String,
    val demonym: String,
    val majorSingle: String,
    val majorPlural: String,
    val ISOnum: Int?,
    val symbol: String,
    val symbolNative: String,
    val minorSingle: String,
    val minorPlural: String,
    val ISOdigits: Int,
    val decimals: Int,
    val numToBasic: Int?
)