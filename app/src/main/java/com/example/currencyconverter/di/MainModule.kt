package com.example.currencyconverter.di

import android.app.Application
import androidx.room.Room
import com.example.currencyconverter.data.dataSource.room.AppDatabase
import com.example.currencyconverter.data.dataSource.room.account.dao.AccountDao
import com.example.currencyconverter.data.dataSource.room.transaction.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return   Room.databaseBuilder(
            context = app,
            klass =  AppDatabase::class.java,
            name =  AppDatabase.Db_name
        ).build()
    }

    @Provides
    fun provideAccountDao(db: AppDatabase): AccountDao = db.accountDao()

    @Provides
    fun provideTransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()
}