package com.example.currencyconverter.presentation.currencies_feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R

@Preview(showBackground = true)
@Composable
fun showCurrenciesUiItem(){
    // CurrenciesScreen()
    LazyColumn (
        modifier=Modifier.fillMaxSize()
    ){
        items (10){
            CurrenciesUiItem()
        }
    }
}
@Composable
fun CurrenciesUiItem(

){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal =6.dp,
                vertical = 4.dp
            )
            .fillMaxWidth()
            .height(80.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = Color.Blue.copy(alpha = 0.13f)
            )
            .padding(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.18f)
            // .background(Color.Red)
            ,
            contentAlignment = Alignment.Center
        ){
            Image(
                //contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.test),
                contentDescription = "test"
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.55f)
                //  .background(Color.Blue)
                .padding(
                    start = 6.dp
                )
        ) {
            Text(
                text = "RUB",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "Russia ruble",
                fontSize = 12.sp
            )
            if(true){
                Text(
                    text = "Balance: 43000",
                    fontSize = 12.sp
                )
            }
        }
        val focusRequester = remember { FocusRequester() }
        //focusRequester.freeFocus()

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                BasicTextField(
                    modifier = Modifier
                        .widthIn(min= 10.dp)
                        .padding(16.dp)
                        .focusRequester(focusRequester),
                    value = "24000",
                    onValueChange = { newValue ->
                    },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Black, // Цвет текста
                    ),
                    decorationBox = { innerTextField ->
                        Column(

                        ) {
                            innerTextField()
                            HorizontalDivider(color = Color.Black, thickness = 1.dp) // Подчеркивание
                        }
                    },
                )
                Spacer(modifier= Modifier.width(4.dp))
            }
        }
    }
}