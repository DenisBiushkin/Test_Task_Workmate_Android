package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllAccountsUseCase(
    private val accountRepository: AccountRepository
) {

    operator fun invoke(): Flow<List<Account>> {
        return accountRepository.getAllAsFlow()
    }
}