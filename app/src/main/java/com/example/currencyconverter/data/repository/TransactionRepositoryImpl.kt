package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.mapper.toDbo
import com.example.currencyconverter.data.mapper.toDomain
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.TransactionRepository

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
):TransactionRepository {
    override suspend fun insertAll(transactions: List<Transaction>) {
        transactionDao.insertAll(*transactions.map { it.toDbo() }.toTypedArray())
    }

    override suspend fun getAll(): List<Transaction> {
        return transactionDao.getAll().map { it.toDomain() }
    }
}