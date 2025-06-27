package com.example.currencyconverter.presentation.currencies_feature.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.presentation.currencies_feature.viewmodel.CurrenciesViewModel

@Composable
fun CurrenciesScreenFull(
    viewModel: CurrenciesViewModel = hiltViewModel<CurrenciesViewModel>()
){


    val state = viewModel.state.collectAsState()
    Log.d("MyTag","----++++++++++++++++++++++++++-----")
    state.value.currencies.forEach {
        Log.d("MyTag","----${it.isEditable}-----")
    }
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
        }
    )
}