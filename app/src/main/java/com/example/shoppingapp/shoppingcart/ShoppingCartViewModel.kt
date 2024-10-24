package com.example.shoppingapp.shoppingcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.products.Product

class ShoppingCartViewModel: ViewModel() {
    private val _shoppingCartProductList = MutableLiveData<List<Product>>()
    val shoppingCartProductList: LiveData<List<Product>> = _shoppingCartProductList

    fun getShoppingCartProductList() {
        _shoppingCartProductList.value = ProductManager.getShoppingCartProductList()
    }

    fun addProductToCart(product: Product) {
        ProductManager.addProductToCart(product)
        getShoppingCartProductList()
    }

}