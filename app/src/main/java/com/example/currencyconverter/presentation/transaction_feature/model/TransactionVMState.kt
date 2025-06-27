package com.example.currencyconverter.presentation.transaction_feature.model

data class TransactionVMState(
    val isLoading:Boolean=true,
    val transactions:List<TransactionUi> = emptyList()
)