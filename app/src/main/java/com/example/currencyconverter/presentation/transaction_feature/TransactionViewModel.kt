package com.example.currencyconverter.presentation.transaction_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.domain.usecase.GetAllTransactionsUseCase
import com.example.currencyconverter.presentation.exchange_feature.model.ExchangeVMState
import com.example.currencyconverter.presentation.transaction_feature.model.TransactionUi
import com.example.currencyconverter.presentation.transaction_feature.model.TransactionVMState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(TransactionVMState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val transactionUi=getAllTransactionsUseCase.invoke().map {
                it.toTransactionUi()
            }
            _state.update {
                it.copy(
                    transactions = transactionUi,
                    isLoading = false
                )
            }
        }
    }


}