package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import java.time.LocalDateTime

class InitializeDefaultAccountUseCase(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {

    private val START_AMOUNT = 75_000.0

    suspend operator fun invoke(){
        if (accountRepository.getAll().isEmpty()){
            accountRepository.insertAll(listOf(
                Account(
                    code = Currency.RUB,
                    amount =  START_AMOUNT
                )
            ))
            saveFirstTransaction()
        }
    }
    private suspend fun saveFirstTransaction(){
        val transaction = Transaction(
            from = Currency.RUB,
            to = Currency.RUB,
            fromAmount =  START_AMOUNT,
            toAmount =  START_AMOUNT,
            dateTime = LocalDateTime.now()
        )
        transactionRepository.insertAll(listOf(transaction))
    }
}