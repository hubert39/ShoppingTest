package com.example.shoppingapp.shoppingcart

import com.example.shoppingapp.products.Product

object ProductManager {
    private val productList = mutableListOf<Product>()

    fun getShoppingCartProductList(): List<Product> {
        return productList
    }

    fun addProductToCart(product: Product) {
        productList.add(product)
    }

}