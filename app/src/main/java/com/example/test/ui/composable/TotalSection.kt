package com.example.test.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TotalSection(total: Int) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.background(Color.LightGray),
            text = "Total: $total"
        )
    }
}
