package com.example.currencyconverter.presentation.currencies_feature.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.currencies_feature.model.CurrencyUI
import java.text.DecimalFormat

@Composable
fun CurrencyInputRow(
    currencyUI: CurrencyUI,
    onAmountChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onInputClick:()-> Unit
) {
    val formatter = DecimalFormat("0.0000")
    var inputValue by remember { mutableStateOf(formatter.format(currencyUI.amount) )}
    val isEditable by rememberUpdatedState(currencyUI.isEditable)

    val textStyle = TextStyle(
        fontSize = 24.sp,
        color = Color.Black,
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currencyUI.symbol,
                style = textStyle
            )

            BasicTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    onAmountChange(it)
                },
                enabled = isEditable,
                textStyle = textStyle,
                modifier = Modifier
                    .widthIn(min = 10.dp,max=90.dp)
                    .padding(start = 5.dp, top = 16.dp, end = 4.dp, bottom = 16.dp)
                    .clickable {
                        onInputClick()
                    }
                ,
                maxLines = 1,
                decorationBox = { innerTextField ->
                    Column {
                        innerTextField()
                        HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    }
                }
            )

            // Показываем крестик, только если поле редактируемое
            if (isEditable) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            inputValue = "1.0"
                            onClearClick()
                        }
                        .padding(end = 4.dp)
                )
            }
        }
    }
}