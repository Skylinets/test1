package com.example.test.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.test.model.Product
import com.example.test.model.ProductInfo
import com.example.test.ui.composable.ButtonSection
import com.example.test.ui.composable.FoodTableScreen
import com.example.test.ui.composable.LodgingTableScreen
import com.example.test.ui.composable.TotalSection

@Composable
fun MyScreen() {
    val productList = remember { mutableStateListOf<ProductInfo>() }
    var foodTotal by remember { mutableIntStateOf(value = 0) }
    var lodgingTotal by remember { mutableIntStateOf(value = 0) }
    val list = remember { mutableStateListOf<Product>().apply { addAll(getProducts()) } }

    Column {
        FoodTableScreen(
            products = list.filter { product -> product.type == "food" },
            onTotalChange = {
                foodTotal = it
                println("food total ->>>> $foodTotal")
            }
        )
        LodgingTableScreen(
            products = list.filter { product -> product.type == "lodging" },
            onTotalChange = {
                lodgingTotal = it
                println("lodging total ->>>> $lodgingTotal")
            }
        )
        TotalSection (foodTotal + lodgingTotal)
        ButtonSection(
            products = list,
            onAddButtonClicked = { products ->
                products.forEach { product ->
                    val existingProduct = productList.find { it.id == product.id }
                    if (product.quantity > 0) {
                        if (existingProduct != null) {
                            existingProduct.quantity = product.quantity
                        } else {
                            productList.add(element = product)
                        }
                    } else {
                        existingProduct?.let { productInfo -> productList.remove(element = productInfo) }
                    }
                }
                productList.forEach { product ->
                    println("ID: ${product.id}, Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}")
                }
                println("${productList.toList()}")
            }
        )
    }
}
fun calculateTotal(products: List<Product>): Int {
    var total = 0
    for (product in products) {
        total += (product.price * product.quantity)
    }
    return total
}

fun getProducts() = listOf(
    Product(
        id = "0001",
        name = "Product 1",
        type = "food",
        price = 100
    ),
    Product(
        id = "0002",
        name = "Product 2",
        type = "food",
        price = 150
    ),
    Product(
        id = "0003",
        name = "Product 3",
        type = "food",
        price = 200
    ),
    Product(
        id = "0004",
        name = "Product 4",
        type = "lodging",
        price = 300
    ),
    Product(
        id = "0005",
        name = "Product 5",
        type = "lodging",
        price = 350
    ),
    Product(
        id = "0006",
        name = "Product 6",
        type = "lodging",
        price = 400
    )
)