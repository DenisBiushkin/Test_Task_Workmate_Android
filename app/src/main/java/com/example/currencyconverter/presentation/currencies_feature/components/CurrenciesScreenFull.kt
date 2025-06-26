package com.example.currencyconverter.presentation.currencies_feature.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.presentation.currencies_feature.viewmodel.CurrenciesViewModel

@Composable
fun CurrenciesScreenFull(
    viewModel: CurrenciesViewModel = hiltViewModel<CurrenciesViewModel>()
){

    val state = viewModel.state.collectAsState()
    CurrenciesScreen(
        state.value.currencies
    )
}