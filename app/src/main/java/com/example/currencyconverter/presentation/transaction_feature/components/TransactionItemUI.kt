package com.example.currencyconverter.presentation.transaction_feature.components


import androidx.compose.foundation.background
import com.example.currencyconverter.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.Transaction
import com.example.currencyconverter.presentation.currencies_feature.components.CurrenciesScreen
import com.example.currencyconverter.presentation.transaction_feature.model.TransactionUi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



@Preview(showBackground = true)
@Composable
fun show4(){
    LazyColumn(

    ) {
        items(5){
            TransactionItemUI(
                TransactionUi(
                    from = Currency.CAD,
                    to = Currency.CAD,
                    fromAmount = "",
                    toAmount = "",
                    dateTime = LocalDateTime.now()
                )
            )
        }
    }
}



@Composable
fun TransactionItemUI(
    transaction: TransactionUi
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(
                color = Color.Blue.copy(alpha = 0.13f),
                shape = RoundedCornerShape(12.dp)
            )
        ,
        elevation =4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()

            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🖼 Иконка слева
            Icon(
                painter = painterResource(R.drawable.transaction), // Или painterResource if custom SVG/PNG
                contentDescription = "Transaction Icon",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 7.dp, end = 12.dp),
                tint = Color.Blue.copy(alpha = 0.7f)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${transaction.from} → ${transaction.to}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${transaction.fromAmount} ${transaction.from} → ${transaction.toAmount} ${transaction.to}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = transaction.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}