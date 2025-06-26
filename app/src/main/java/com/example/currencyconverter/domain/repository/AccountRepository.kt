package com.example.currencyconverter.domain.repository

import androidx.room.Query
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.domain.entity.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun insertAll(accounts: List<Account>)

    suspend fun getAll(): List<Account>

    fun getAllAsFlow(): Flow<List<Account>>
}


