package com.example.test.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChanged: (Int) -> Unit
) {
    val count = remember { mutableIntStateOf(value = quantity) }
    Row {
        Text(
            modifier = Modifier
                .background(color = Color.Red)
                .clip(RoundedCornerShape(size = 12.dp))
                .size(size = 30.dp)
                .clickable {
                    if (count.intValue > 0) {
                        count.intValue -= 1
                        onQuantityChanged(count.intValue)
                    }
                },
            text = "-",
            style = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.width(width = 12.dp))
        Text(text = "${count.intValue}")
        Spacer(modifier = Modifier.width(width = 12.dp))
        Text(
            modifier = Modifier
                .background(color = Color.Green)
                .clip(RoundedCornerShape(size = 12.dp))
                .size(size = 30.dp)
                .clickable {
                    count.intValue += 1
                    onQuantityChanged(count.intValue)
                },
            text = "+",
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}