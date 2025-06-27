package com.example.currencyconverter.presentation.currencies_feature.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.currencyconverter.presentation.currencies_feature.model.ContentState
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI

@Preview(showBackground = true)
@Composable
fun showCurrenciesScreen(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrenciesScreen(
    contentState: ContentState,
    currencyUIList: List<CurrencyUI>,
    onAmountChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onInputClick:()-> Unit,
    onCurrencyClick: (CurrencyUI) -> Unit
){

    Scaffold(
        topBar = {
           TopAppBar(
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.Magenta.copy(alpha = 0.2f)
               ),
               title = {
                   Text(
                       modifier = Modifier.padding(start = 3.dp),
                       text = "Валюты:(${contentState.name})",
                       fontWeight = FontWeight.Bold,
                       fontSize = 24.sp
                       )
               },
               navigationIcon = {},
               actions = {
                    Icon(
                        modifier = Modifier.
                            padding(end = 10.dp)
                            .size(30.dp),
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.Black
                    )
               }
           )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {  },
                    label = { Text("") },
                    selected = true,
                    onClick = {
                    }
                )
            }
        }
    ) {
        paddingValues->
        LazyColumn (
            modifier=Modifier
                .padding( paddingValues)
                .fillMaxSize()
        ){
            items(
                currencyUIList,
                key = { it.currency }
            ){
               // index,item->
                CurrenciesUiItem(
                    currencyUI = it,
                    onAmountChange =onAmountChange,
                    onClearClick =  onClearClick,
                    onInputClick =  onInputClick,
                    onCurrencyClick = {
                        onCurrencyClick(it)
                    }
                )
            }
        }
    }
}