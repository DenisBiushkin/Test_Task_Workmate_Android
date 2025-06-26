package com.example.currencyconverter.presentation.currencies_feature.model

sealed class ContentState{
    object Input:ContentState()
    object List:ContentState()
}