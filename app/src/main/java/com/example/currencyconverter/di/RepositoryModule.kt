package com.example.currencyconverter.di

import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import com.example.currencyconverter.data.repository.AccountRepositoryImpl
import com.example.currencyconverter.data.repository.RatesRepositoryImpl
import com.example.currencyconverter.data.repository.TransactionRepositoryImpl
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.RatesRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRatesRepository(): RatesRepository {
        return RatesRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(dao: AccountDao): AccountRepository {
        return AccountRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(dao: TransactionDao): TransactionRepository {
        return TransactionRepositoryImpl(dao)
    }
}