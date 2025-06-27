package com.example.currencyconverter.domain.entity

import com.example.currencyconverter.presentation.transaction_feature.model.TransactionUi
import java.text.DecimalFormat
import java.time.LocalDateTime

data class Transaction(
    val id: Int =0,
    val from: Currency,
    val to: Currency,
    val fromAmount:  Double,
    val toAmount: Double,
    val dateTime: LocalDateTime,
){
    fun toTransactionUi():TransactionUi = this.run{
        val formatter = DecimalFormat("0.000")
        TransactionUi(
            from = from,
            to = to,
            fromAmount =formatter.format( fromAmount),
            toAmount =formatter.format(toAmount),
            dateTime = dateTime
        )
    }
}