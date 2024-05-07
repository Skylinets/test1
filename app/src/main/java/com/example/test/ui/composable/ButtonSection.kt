package com.example.test.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.test.model.Product
import com.example.test.model.ProductInfo

@Composable
fun ButtonSection(
    products: List<Product>,
    onAddButtonClicked: (List<ProductInfo>) -> Unit
){
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val productInfoList = products.map { product ->
                    ProductInfo(product.id, product.name, product.price, product.quantity)
                }
                onAddButtonClicked(productInfoList)
            }
        ) {
            Text(text = "Save data")
        }
    }
}