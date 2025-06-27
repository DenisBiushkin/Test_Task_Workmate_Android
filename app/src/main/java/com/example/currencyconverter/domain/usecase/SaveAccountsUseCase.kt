package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.repository.AccountRepository

class SaveAccountsUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(accounts: List<Account>) {
        accountRepository.insertAll(accounts)
    }
}