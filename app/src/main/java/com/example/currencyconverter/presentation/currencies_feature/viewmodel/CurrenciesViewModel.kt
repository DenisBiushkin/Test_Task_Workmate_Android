package com.example.currencyconverter.presentation.currencies_feature.viewmodel

import android.util.Log
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Rate
import com.example.currencyconverter.domain.entity.toCurrencyCode
import com.example.currencyconverter.domain.usecase.GetAllAccountsUseCase
import com.example.currencyconverter.domain.usecase.GetRatesUseCase
import com.example.currencyconverter.domain.usecase.InitializeDefaultAccountUseCase
import com.example.currencyconverter.presentation.currencies_feature.model.ContentState
import com.example.currencyconverter.presentation.currencies_feature.model.CurrenciesVMState
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import com.example.currencyconverter.util.CurrencyRepository
import com.example.currencyconverter.util.toCurrencyUI
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
                    amount = 1.0
                )
                val currencyUI=getCurrencyUI(newRates)
                _state.update { it.copy(
                    currencies = currencyUI,
                    isLoading = false
                ) }
                delay(1000)
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

    private fun getCurrencyUI(newRates: List<Rate>):List<CurrencyUI>{
        val list= newRates.map {

            toCurrencyUI(it,accountsMap).copy(
                amount =it.rate*state.value.valueCurrency,
                isEditable = ((state.value.selectedCurrency.name==it.currencyCode.name)
                        && (ContentState.Input == state.value.contentState))
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
        return list
    }

    private fun moveItemToTop(code: String, list: List<CurrencyUI>): List<CurrencyUI> {
        var item = list.find { it.currency== code } ?: return list
        item=item.copy(isEditable = true)
        val others = list.filterNot { it.currency == code }
            .map { it.copy(isEditable = false) }
        return listOf(item) + others
    }

    fun onSelectNewCurrency(currencyUI: CurrencyUI){
        if(currencyUI.currency==state.value.selectedCurrency.name)
            return
        when(_state.value.contentState){
            ContentState.Input -> toExchange(currencyUI)
            ContentState.List -> toMoveCurrency(currencyUI)
        }
    }

    private fun toMoveCurrency(currencyUI: CurrencyUI){
        //переходить на Enum Currency было ошибка)
        _state.update {
            val movedItemList=moveItemToTop(currencyUI.currency,it.currencies)
            Log.d(TAG,movedItemList.toString())
            it.copy(
                currencies =movedItemList,
                selectedCurrency = currencyUI.currency.toCurrencyCode()!!,
                valueCurrency = 1.0,
            )
        }
    }

    private fun toExchange(currencyUI: CurrencyUI){
        Log.d(TAG,"Валюта выбрана, вперед за обменом")
        _state.update {
           it.copy(
               accessNavigate = true,
               fromSelectedCurrency = currencyUI.currency
           )
        }
    }

    private fun onChangeValueInput(): List<CurrencyUI> {
        return state.value.currencies.map {
            if(it.currency==state.value.selectedCurrency.name){
                it.copy(isEditable = false)
            }else{ it }
        }
    }

    fun onChangeValueCurrency(input:String){
        if(input.toDoubleOrNull() != null){
            _state.update {
                it.copy(
                    valueCurrency = input.toDoubleOrNull()!!,
                )
            }
        }
    }

    fun onClearInput(){
        _state.update {
            it.copy(
                valueCurrency = 1.0,
                contentState = ContentState.List,
                currencies = onChangeValueInput()
            )
        }
    }

    fun onClickToChangeInput(){
        _state.update {
            it.copy(contentState = ContentState.Input)
        }
    }

    fun clearState(){
        _state.update {
            CurrenciesVMState()
        }
    }
}