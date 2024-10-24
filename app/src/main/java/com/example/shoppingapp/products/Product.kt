package com.example.shoppingapp.products

import com.example.shoppingapp.R

data class Product(
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val productImageId: Int
)

val productList = arrayListOf(
    Product(
        1,
        "Product 1",
        "Description 1",
        10.0,
        R.drawable.pin
    ),
    Product(
        2,
        "Product 2",
        "Description 2",
        20.0,
        R.drawable.camp
    ),
    Product(
        3,
        "Product 3",
        "Description 3",
        30.0,
        R.drawable.ladder
    ),
)