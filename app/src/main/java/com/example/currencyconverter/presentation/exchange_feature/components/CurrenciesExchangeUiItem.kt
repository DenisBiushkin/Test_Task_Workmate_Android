package com.example.currencyconverter.presentation.exchange_feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.presentation.currencies_feature.components.CurrenciesUiItem
import com.example.currencyconverter.presentation.currencies_feature.components.CurrencyInputRow
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import java.text.DecimalFormat


@Preview(showBackground = true)
@Composable
fun sho3(){

}


@Composable
fun CurrenciesExchangeUiItem(
    currency: CurrencyUI,
    specSymbol: String = ""
){
    CurrenciesUiItem(
        currencyUI =currency,
        onCurrencyClick = {

        },
        contentCurrency = {
            val textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                val formatter = DecimalFormat("0.00")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = specSymbol,
                        style = textStyle
                    )
                    Text(
                        text = currency.symbol,
                        style = textStyle
                    )
                    Text(
                        modifier = Modifier.widthIn(min = 10.dp,max=90.dp)
                            .padding(start = 5.dp, top = 16.dp, end = 4.dp, bottom = 16.dp),
                        text = formatter.format(currency.amount),
                        style = textStyle
                    )
                }
            }
        }
    )
}