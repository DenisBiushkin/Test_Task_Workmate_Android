package com.example.currencyconverter.domain.repository

import androidx.room.Query
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.domain.entity.Transaction

interface TransactionRepository {

    suspend fun insertAll(transactions: List<Transaction>)

    suspend fun getAll(): List<Transaction>
}
