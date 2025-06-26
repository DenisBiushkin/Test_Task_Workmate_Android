package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.entity.toCurrencyCode
import java.time.LocalDateTime

fun TransactionDbo.toDomain(): Transaction {
    return Transaction(
        id=id,
        from = from.toCurrencyCode()?:Currency.USD,
        to= to.toCurrencyCode()?:Currency.USD,
        fromAmount= fromAmount,
        toAmount=toAmount,
        dateTime=dateTime,
    )
}

fun  Transaction.toDbo(): TransactionDbo {
    return TransactionDbo(
        id=id,
        from = from.name,
        to= to.name,
        fromAmount= fromAmount,
        toAmount=toAmount,
        dateTime=dateTime,
    )
}