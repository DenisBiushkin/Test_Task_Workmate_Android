package com.example.currencyconverter.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.currencyconverter.presentation.currencies_feature.components.CurrenciesScreenFull
import com.example.currencyconverter.presentation.exchange_feature.components.CurrencyExchangeScreenFull
import com.example.currencyconverter.presentation.transaction_feature.components.TransactionScreenFull
import com.example.currencyconverter.util.Constans

@Composable
fun NavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.CurrenciesScreen.route
    ){
        composable (
            route = Screens.CurrenciesScreen.route
        ){
            CurrenciesScreenFull(
                navController =navHostController
            )
        }

        composable (
            route = Screens.ExchangeScreen.route,
            arguments = listOf(
                navArgument(Constans.EXCHANGE_FROM_CURRENCY_ARG) { type = NavType.StringType },
                navArgument(Constans.EXCHANGE_TO_CURRENCY_ARG) { type = NavType.StringType },
                navArgument(Constans.EXCHANGE_AMOUNT_ARG) { type = NavType.FloatType }
            )
        ){
            CurrencyExchangeScreenFull(
                navController = navHostController
            )
        }

        composable (
            route = Screens.TransactionScreen.route,
        ){
            TransactionScreenFull(
                navController = navHostController
            )
        }
    }
}