package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.TransactionRepository

class GetAllTransactionsUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke():List<Transaction>{
        return transactionRepository.getAll()
    }
}