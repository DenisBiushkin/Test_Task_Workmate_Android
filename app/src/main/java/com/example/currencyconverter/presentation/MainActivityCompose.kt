package com.example.currencyconverter.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.currencyconverter.presentation.currencies_feature.components.CurrenciesScreenFull
import com.example.currencyconverter.presentation.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.domain.entity.CurrencyMeta
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrenciesScreenFull()
                }
            }
        }
    }
}
