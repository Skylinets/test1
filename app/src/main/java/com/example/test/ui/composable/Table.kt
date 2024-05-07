package com.example.test.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.test.model.Product

@Composable
fun Table(
    products: List<Product>,
    onQuantityChanged: (Product, Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(products) { product ->
                TableRow(
                    product = product,
                    onQuantityChanged = onQuantityChanged
                )
            }
        }
    }
}