package com.example.currencyconverter.presentation.transaction_feature.model

import com.example.currencyconverter.domain.entity.Currency
import java.time.LocalDateTime

data class TransactionUi (
    val from: Currency,
    val to: Currency,
    val fromAmount:  String,
    val toAmount: String,
    val dateTime: LocalDateTime,

)