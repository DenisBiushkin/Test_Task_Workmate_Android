package com.example.currencyconverter.presentation.currencies_feature.model

sealed class ContentState(val name: String){
    object Input:ContentState("Ввод")
    object List:ContentState("Список")
}