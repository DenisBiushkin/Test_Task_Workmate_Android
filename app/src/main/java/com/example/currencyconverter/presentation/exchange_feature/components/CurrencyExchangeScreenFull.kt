package com.example.currencyconverter.presentation.exchange_feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.currencyconverter.presentation.exchange_feature.viewmodel.ExchangeViewModel
import com.example.currencyconverter.presentation.navigation.Screens

@Composable
fun CurrencyExchangeScreenFull(
    navController: NavController,
    viewModel: ExchangeViewModel = hiltViewModel<ExchangeViewModel>()
){
    val state = viewModel.state.collectAsState()
    if (state.value.accessNavigate){
        navController.navigate(Screens.CurrenciesScreen.route)
    }
    if (state.value.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        CurrencyExchangeScreen(
            currencyExchange = state.value.currencyExchange,
            listTransaction = state.value.listTransaction,
            onExchangeClick={
                viewModel.toExchangeCurrency()
            }
        )
    }

}