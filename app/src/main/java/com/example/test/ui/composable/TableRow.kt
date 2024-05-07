package com.example.test.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.model.Product

@Composable
fun TableRow(
    product: Product,
    onQuantityChanged: (Product, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.width(width = 100.dp),
            text = product.name
        )
        Text(text = "${product.price}")

        Spacer(modifier = Modifier.weight(weight = 1f))

        QuantitySelector(
            quantity = product.quantity,
            onQuantityChanged = { newQuantity ->
                onQuantityChanged(product, newQuantity)
            }
        )
    }
}