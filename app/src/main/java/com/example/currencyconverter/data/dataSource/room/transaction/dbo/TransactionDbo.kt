package com.example.currencyconverter.data.dataSource.room.transaction.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.currencyconverter.data.dataSource.room.converter.Converters
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(tableName = "transactions")
@TypeConverters(Converters::class)
data class TransactionDbo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "currency_code_from")
    val from: String,
    @ColumnInfo(name = "currency_code_to")
    val to: String,
    @ColumnInfo(name = "amount_from")
    val fromAmount: Double,
    @ColumnInfo(name = "amount_to")
    val toAmount: Double,
    val dateTime: LocalDateTime,
)