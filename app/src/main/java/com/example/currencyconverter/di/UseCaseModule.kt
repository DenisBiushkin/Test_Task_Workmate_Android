package com.example.currencyconverter.di

import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.RatesRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import com.example.currencyconverter.domain.usecase.GetAllAccountsUseCase
import com.example.currencyconverter.domain.usecase.GetAllTransactionsUseCase
import com.example.currencyconverter.domain.usecase.GetRatesUseCase
import com.example.currencyconverter.domain.usecase.InitializeDefaultAccountUseCase
import com.example.currencyconverter.domain.usecase.SaveAccountsUseCase
import com.example.currencyconverter.domain.usecase.SaveTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideInitializeDefaultAccountUseCase(
        accountRepository: AccountRepository,
        transactionRepository: TransactionRepository
    ): InitializeDefaultAccountUseCase {
        return InitializeDefaultAccountUseCase(accountRepository,transactionRepository)
    }

    @Provides
    fun provideGetRatesUseCase(
        ratesRepository: RatesRepository
    ): GetRatesUseCase {
        return GetRatesUseCase(ratesRepository)
    }

    @Provides
    fun provideGetAllTransactionsUseCase(
        transactionRepository: TransactionRepository
    ): GetAllTransactionsUseCase {
        return GetAllTransactionsUseCase(transactionRepository)
    }

    @Provides
    fun provideGetAllAccountsUseCase(
        accountRepository: AccountRepository
    ): GetAllAccountsUseCase {
        return GetAllAccountsUseCase(accountRepository)
    }

    @Provides
    fun provideSaveTransactionUseCase(
        transactionRepository: TransactionRepository
    ): SaveTransactionUseCase {
        return SaveTransactionUseCase(transactionRepository)
    }

    @Provides
    fun provideSaveAccountsUseCase(
        accountRepository: AccountRepository
    ):SaveAccountsUseCase {
        return SaveAccountsUseCase(accountRepository)
    }


}