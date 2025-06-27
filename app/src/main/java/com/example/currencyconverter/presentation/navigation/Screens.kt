package com.example.currencyconverter.presentation.navigation

import androidx.compose.ui.geometry.RoundRect
import com.example.currencyconverter.util.Constans

sealed class Screens(
    val route:String
) {
    object CurrenciesScreen:Screens(route = "main_route"){
    }

    object ExchangeScreen:Screens(route = "exchange_route/{${
        Constans.EXCHANGE_FROM_CURRENCY_ARG}}/{" +
            "${Constans.EXCHANGE_TO_CURRENCY_ARG}}/{${Constans.EXCHANGE_AMOUNT_ARG}}"){
        fun withArgs(
            fromCurrencyArg:String
            ,toCurrencyArg:String,
            amount: Float
        ):String{
            return "exchange_route/$fromCurrencyArg/$toCurrencyArg/$amount"
        }
    }

    object TransactionScreen:Screens(route = "transaction_route")
}