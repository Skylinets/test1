package com.example.test.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.model.Product
import com.example.test.ui.calculateTotal

@Composable
fun FoodTableScreen(
    products: List<Product>,
    onTotalChange: (Int) -> Unit,
) {
    var subtotal by remember { mutableIntStateOf(value = 0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Table(
            products = products,
            onQuantityChanged = { product, newQuantity ->
                product.quantity = newQuantity
                subtotal = calculateTotal(products = products)
                onTotalChange(subtotal)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total: $subtotal")
    }
}