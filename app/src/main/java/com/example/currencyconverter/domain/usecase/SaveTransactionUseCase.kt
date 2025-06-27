package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.TransactionRepository

class SaveTransactionUseCase(
    private val transactionRepository: TransactionRepository

) {

    suspend operator fun invoke(transactions: List<Transaction>) {
        transactionRepository.insertAll(transactions)
    }
}