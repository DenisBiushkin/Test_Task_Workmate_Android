package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.mapper.toDbo
import com.example.currencyconverter.data.mapper.toDomain
import com.example.currencyconverter.domain.entity.Account
import com.example.currencyconverter.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun insertAll(accounts: List<Account>) {
        accountDao.insertAll(*accounts.map { it.toDbo() }.toTypedArray())
    }

    override suspend fun getAll(): List<Account> {
       return accountDao.getAll().map { it.toDomain() }
    }

    override fun getAllAsFlow(): Flow<List<Account>> {
        return accountDao.getAllAsFlow().map { list -> list.map { it.toDomain() } }
    }

}