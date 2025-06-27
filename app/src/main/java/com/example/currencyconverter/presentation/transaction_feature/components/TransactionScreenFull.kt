package com.example.currencyconverter.presentation.transaction_feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.currencyconverter.presentation.transaction_feature.TransactionViewModel

@Composable
fun TransactionScreenFull(
    viewModel: TransactionViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state.collectAsState()

    if (state.value.isLoading){
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        TransactionScreen(
            transactions = state.value.transactions,
            onBackClick = {

            }
        )
    }
}