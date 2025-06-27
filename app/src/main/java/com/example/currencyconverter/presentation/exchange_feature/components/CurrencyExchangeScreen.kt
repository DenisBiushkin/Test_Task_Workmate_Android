package com.example.currencyconverter.presentation.exchange_feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import com.example.currencyconverter.presentation.exchange_feature.model.CurrencyExchange
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyExchangeScreen(
    listTransaction: List<CurrencyUI>,
    currencyExchange: CurrencyExchange,
    onExchangeClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta.copy(alpha = 0.2f)
                ),
                title = {
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = "Обмен",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {},
                actions = {
                }
            )
        }
    ){
        paddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top=8.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val formatter = DecimalFormat("0.00")
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "${currencyExchange.fromCurrency} to ${currencyExchange.toCurrency}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "${currencyExchange.toSymbol}1 = ${currencyExchange.fromSymbol}${currencyExchange.rate}", fontSize = 16.sp)
            }


            LazyColumn {
                itemsIndexed(listTransaction) {
                        index,item->
                    CurrenciesExchangeUiItem(
                        item
                    )
                }
            }
//        CurrencyCard(
//            currencyCode = "EUR",
//            currencyName = "Euro",
//            amount = "+€150,00",
//            imageResId = R.drawable.eur_flag
//        )
//
//        CurrencyCard(
//            currencyCode = "RUB",
//            currencyName = "Russia Rouble",
//            amount = "-P13.597,50",
//            imageResId = R.drawable.rub_flag,
//            balance = "Balance: P43.788.11"
//        )
//

            Button(onClick = onExchangeClick) {
                Text(text = "Buy ${currencyExchange.toCurrency} for ${currencyExchange.fromCurrency}")
            }
        }
    }



}