package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen()
        }
    }
}

@Composable
fun MyScreen() {
    val productList = remember { mutableStateListOf<ProductInfo>() }
    var foodTotal by remember { mutableIntStateOf(value = 0) }
    var lodgingTotal by remember { mutableIntStateOf(value = 0) }
    var total by remember { mutableIntStateOf(value = 0) }
    val total2 = foodTotal + lodgingTotal
    val list = getProducts()

    Column {
        FoodTableScreen(
            products = list.filter { product -> product.type == "food" },
            onTotalChange = {
                foodTotal = it
                println("food total ->>>> $foodTotal")
                //total += it
            }
        )
        LodgingTableScreen(
            products = list.filter { product -> product.type == "lodging" },
            onTotalChange = {
                lodgingTotal = it
                println("lodging total ->>>> $lodgingTotal")
            }
        )
        TotalSection(total = total2)
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

@Composable
fun LodgingTableScreen(
    products: List<Product>,
    onTotalChange: (Int) -> Unit
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

fun calculateTotal(products: List<Product>): Int {
    var total = 0
    for (product in products) {
        total += product.price * product.quantity
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

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val type: String,
    var quantity: Int = 0
)

data class ProductInfo(
    val id: String,
    val name: String,
    val price: Int,
    var quantity: Int
)
