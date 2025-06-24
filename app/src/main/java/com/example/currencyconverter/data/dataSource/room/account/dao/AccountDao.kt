package com.example.currencyconverter.data.dataSource.room.account.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg accounts: AccountDbo)

    @Query("SELECT * FROM accounts")
    suspend fun getAll(): List<AccountDbo>

    @Query("SELECT * FROM accounts")
    fun getAllAsFlow(): Flow<List<AccountDbo>>
}