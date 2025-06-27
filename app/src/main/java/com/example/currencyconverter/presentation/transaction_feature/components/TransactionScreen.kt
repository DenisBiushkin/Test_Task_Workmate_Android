package com.example.currencyconverter.presentation.transaction_feature.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.presentation.transaction_feature.model.TransactionUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    transactions: List<TransactionUi>,
    onBackClick: () -> Unit
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
                        text = "Транзакции",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {},
                actions = {

                }
            )
        },
    ) {
        paddingValues->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            reverseLayout = true
        ) {
            items(transactions){
                TransactionItemUI(transaction = it)
            }
        }

    }
}