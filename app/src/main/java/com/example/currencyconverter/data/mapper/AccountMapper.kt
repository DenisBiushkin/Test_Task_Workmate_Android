package com.example.currencyconverter.data.mapper

import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.toCurrencyCode

fun AccountDbo.toDomain(): Account {
    return Account(
        code = code.toCurrencyCode()?:Currency.RUB,
        amount =amount
    )
}

fun Account.toDbo(): AccountDbo {
    return AccountDbo(
        code = code.name,
        amount =amount
    )
}