package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.repository.AccountRepository

class InitializeDefaultAccountUseCase(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(){
        if (accountRepository.getAll().isEmpty()){
            accountRepository.insertAll(listOf(
                Account(
                    code = Currency.RUB,
                    amount = 75_000.0
                )
            ))
        }
    }
}