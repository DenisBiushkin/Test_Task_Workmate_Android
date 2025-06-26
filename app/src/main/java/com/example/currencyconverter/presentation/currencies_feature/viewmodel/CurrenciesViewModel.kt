package com.example.currencyconverter.presentation.currencies_feature.viewmodel

import android.util.Log
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.usecase.GetAllAccountsUseCase
import com.example.currencyconverter.domain.usecase.GetRatesUseCase
import com.example.currencyconverter.domain.usecase.InitializeDefaultAccountUseCase
import com.example.currencyconverter.presentation.currencies_feature.model.ContentState
import com.example.currencyconverter.presentation.currencies_feature.model.CurrenciesVMState
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import com.example.currencyconverter.util.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val initializeDefaultAccountUseCase: InitializeDefaultAccountUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    private val getAllAccountsUseCase: GetAllAccountsUseCase,

):ViewModel() {
    private val TAG="MyTag"

    private var initializerJob : Job? = null
    private var ratesJob : Job? = null
    private var accountJob : Job? = null

    private var isActiveSurvey : Boolean = true
    private var accountsMap = mutableMapOf<String,Account>()

    private val _state = MutableStateFlow(CurrenciesVMState())
    val state = _state.asStateFlow()

    init {
        initializerBD()
        getAccounts()
        surveyOfCourses()
        CurrencyRepository.meta.forEach{
            Log.d(TAG,it.toString())
        }
    }

    private fun initializerBD(){
        initializerJob?.cancel()
        initializerJob=viewModelScope.launch {
            initializeDefaultAccountUseCase.invoke()
        }
    }

    private fun surveyOfCourses(){
        ratesJob?.cancel()
        ratesJob=viewModelScope.launch {
            while (isActiveSurvey){
                val newRates=getRatesUseCase.invokes(
                    baseCurrencyCode = state.value.selectedCurrency,
                    amount = state.value.valueCurrency
                )

                val currencyUI=getCurrencyUI(newRates)

                _state.update { it.copy(currencies = currencyUI) }
                delay(1000)
            }
        }
    }

    private fun getCurrencyUI(newRates: List<Rate>):List<CurrencyUI>{
        val formatter = DecimalFormat("0.00")
        return newRates.map {
            val strCurrency = it.currencyCode.name
            val pathSvg = CurrencyRepository.currencyLocaleMap[strCurrency]?:"ru"

            CurrencyUI(
                currency =strCurrency,
                name = CurrencyRepository.meta[strCurrency]?.name ?:"Unknown",
                symbol = CurrencyRepository.meta[strCurrency]?.symbol ?:"Unknown",
                svgAssetPath = "file:///android_asset/svg/$pathSvg.svg",
                amount =it.rate*state.value.valueCurrency,
                balance = accountsMap[strCurrency]?.amount ?: 0.0,
                showBalance = accountsMap[strCurrency]!=null
            )

        }.filter {
            when(_state.value.contentState){
                ContentState.Input -> {
                    ( (it.amount<=it.balance) && it.showBalance)
                            ||
                    (it.currency==state.value.selectedCurrency.name)
                }
                ContentState.List -> { true }
            }
        }
    }

    private fun getAccounts(){
        accountJob?.cancel()
        accountJob=getAllAccountsUseCase.invoke().onEach {
            accounts->
            accountsMap=accounts.map { it.code.name to it }.toMutableStateMap()
        }.launchIn(viewModelScope)
    }




}