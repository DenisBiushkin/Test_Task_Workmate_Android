package com.example.currencyconverter.presentation.exchange_feature.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.usecase.GetAllAccountsUseCase
import com.example.currencyconverter.domain.usecase.GetRatesUseCase
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import com.example.currencyconverter.presentation.exchange_feature.model.CurrencyExchange
import com.example.currencyconverter.presentation.exchange_feature.model.ExchangeVMState
import com.example.currencyconverter.util.Constans
import com.example.currencyconverter.util.toCurrencyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRatesUseCase: GetRatesUseCase,
    private val getAllAccountsUseCase: GetAllAccountsUseCase,
) :ViewModel() {

    private var ratesJob : Job? = null
    private var accountJob : Job? = null

    private var accountsMap = mutableMapOf<String,Account>()

    private val _state = MutableStateFlow(ExchangeVMState())
    val state = _state.asStateFlow()

    init{
        getArguments()
    }
    private fun getArguments(){
        val fromCurrency = savedStateHandle.get<String>(Constans.EXCHANGE_FROM_CURRENCY_ARG)
        val toCurrency = savedStateHandle.get<String>(Constans.EXCHANGE_TO_CURRENCY_ARG)
        val amount = savedStateHandle.get<Float>(Constans.EXCHANGE_AMOUNT_ARG)

        if(fromCurrency.isNullOrBlank() && fromCurrency.isNullOrBlank() && amount!=null)
            return
        try {
            _state.update {
                it.copy(
                    fromCurrency= Currency.valueOf(fromCurrency.toString()),
                    toCurrency= Currency.valueOf(toCurrency.toString()),
                    amount = amount!!.toDouble()
                )
            }
            initVM()
        }catch (e:Exception){
            return
        }
    }

    private fun initVM(){
        viewModelScope.launch{
            getRate()
            getAccounts()

        }
    }
    private suspend fun getRate(){
        val rate=getRatesUseCase.invokes(
            baseCurrencyCode = state.value.toCurrency,
            amount = 1.0
        ).find { it.currencyCode==state.value.fromCurrency }
        rate?.let {
                rate->
            _state.update { it.copy(rate=rate) }
            Log.d("MyTag","rates: $rate")
        }
    }

    private suspend fun getAccounts(){
           getAllAccountsUseCase.invoke().collect {
               accountsMap = it.associate {
                   it.code.name to it
               } as MutableMap<String, Account>
               makeTransactionList()
           }
    }

    private fun makeTransactionList(){
        val rate = listOf<Rate>(
            Rate(_state.value.toCurrency,1.0),
            Rate(state.value.fromCurrency,1.0)
        )
        var list= rate.map { toCurrencyUI(it,accountsMap) }.toMutableList()

        list[0]=list[0].copy(amount = state.value.amount)
        list[1]=list[1].copy(amount = state.value.amount*_state.value.rate.rate)
        Log.d("MyTag","list: $list")
        val formatter = DecimalFormat("0.00")
        val currencyExchange= CurrencyExchange(
            fromCurrency = list[1].name,
            fromSymbol = list[0].symbol,
            toCurrency = list[0].name,
            toSymbol = list[1].symbol,
            rate = formatter.format(_state.value.rate.rate)
        )
        _state.update {
            it.copy(
                listTransaction=list,
                currencyExchange = currencyExchange,
                isLoading = false

            )
        }
    }

    fun toExchangeCurrency(){

    }
}