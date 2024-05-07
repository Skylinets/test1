package com.example.test.model

data class Product(
    val id: String,
    val name: String,
    var price: Int,
    val type: String,
    var quantity: Int = 0
)