package com.example.currencyconverter.presentation.navigation

import androidx.compose.ui.geometry.RoundRect

sealed class Screens(
    val route:String
) {
    object CurrenciesScreen:Screens(route = "main_route")

    object ExchangeScreen:Screens(route = "exchange_route")

    object TransactionScreen:Screens(route = "transaction_route")
}