package com.example.shoppingapp.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.shoppingapp.login.Routes.MainRoute.ShoppingCart.toShoppingCart
import com.example.shoppingapp.shoppingcart.ShoppingCartViewModel

@Composable
fun ProductDetailScreen(productId: Int, navController: NavController) {
    val product = productList.first{ it.productId == productId }
    val shoppingCartViewModel = ViewModelProvider(navController.currentBackStackEntry!!).get(ShoppingCartViewModel::class.java)

    Scaffold(
        topBar = {
            AppBar(
                title = "Product Details", imageVector = Icons.Filled.Close) {
                navController.popBackStack()
            }
        }
    ) {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProductPicture(product)
                ProductContent(product)

                AddToCartButton2(shoppingCartViewModel, product, navController)
            }
        }
    }
}

@Composable
fun AddToCartButton2(shoppingCartViewModel: ShoppingCartViewModel, product: Product, navController: NavController) {
    Column{
        Button(
            onClick = {
                shoppingCartViewModel.addProductToCart(product)
                navController.toShoppingCart()
            },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp).semantics {
                contentDescription = "AddToCartButton"
            }
            //modifier = Modifier.padding(start = 16.dp, top = 16.dp).testTag("AddToCartButton")
        ) {
            Text(text = "Add to Cart")
        }
    }
}
