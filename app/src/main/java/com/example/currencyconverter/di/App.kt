package com.example.currencyconverter.di

import android.app.Application
import androidx.room.Room
import com.example.currencyconverter.data.dataSource.room.AppDatabase
import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.domain.usecase.InitializeDefaultAccountUseCase
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton


@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
    }
}