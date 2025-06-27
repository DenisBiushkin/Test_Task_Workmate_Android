package com.example.currencyconverter.presentation.currencies_feature.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.currencyconverter.presentation.currencies_feature.viewmodel.CurrenciesViewModel
import com.example.currencyconverter.presentation.navigation.Screens

@Composable
fun CurrenciesScreenFull(
    navController: NavController,
    viewModel: CurrenciesViewModel = hiltViewModel<CurrenciesViewModel>()
){


    val state = viewModel.state.collectAsState()
    if (state.value.accessNavigate){
        navController.navigate(Screens.ExchangeScreen.withArgs(
            fromCurrencyArg = state.value.fromSelectedCurrency,
            toCurrencyArg = state.value.selectedCurrency.name,
            amount = state.value.valueCurrency.toFloat()
        ))
        viewModel.clearState()
    }

    if (state.value.isLoading){
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        CurrenciesScreen(
            contentState = state.value.contentState,
            currencyUIList =  state.value.currencies,
            onInputClick = {
                viewModel.onClickToChangeInput()
            },
            onCurrencyClick = {
                viewModel.onSelectNewCurrency(it)
            },
            onClearClick = {
                viewModel.onClearInput()
            },
            onAmountChange = {
                viewModel.onChangeValueCurrency(it)
            },
            onTransactionClick ={
                navController.navigate(Screens.TransactionScreen.route)
            }
        )
    }

}