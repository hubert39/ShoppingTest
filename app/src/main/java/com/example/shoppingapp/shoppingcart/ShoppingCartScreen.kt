package com.example.shoppingapp.shoppingcart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shoppingapp.login.Routes.MainRoute.CheckOutInfo.toCheckOutInfo
import com.example.shoppingapp.products.AppBar
import com.example.shoppingapp.products.Product
import com.example.shoppingapp.products.ProductContent
import com.example.shoppingapp.products.ProductPicture
import com.example.shoppingapp.products.productList

@Composable
fun ShoppingCartScreen(shoppingCartViewModel: ShoppingCartViewModel, navController: NavController) {
    val shoppingCartProductList by shoppingCartViewModel.shoppingCartProductList.observeAsState()

    Column() {
        ShoppingCartList(productList.subList(0,2), navController)
        //shoppingCartProductList?.let { ShoppingCartList(it, navController) }
        //ShoppingCartList(cartProducts.getShoppingCartProductList(), navController)
    }
}

@Composable
fun ShoppingCartList(shoppingCartItems: List<Product>, navController: NavController) {
    Scaffold (
        topBar = {
            AppBar(title = "Shopping Cart", imageVector = Icons.Filled.Close) {
                navController.popBackStack()
            }
        }
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(shoppingCartItems) { shoppingCartItem ->
                    ShoppingCartCard(shoppingCartItem) {
                        //navController.popBackStack()
                    }
                }
            }

            CheckoutButton(
                onCheckoutClick = {
                    navController.toCheckOutInfo()
                }
            )
        }
    }

}

@Composable
fun ShoppingCartCard(shoppingCartItem: Product, clickAction: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction(shoppingCartItem) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductPicture(shoppingCartItem)
            ProductContent(shoppingCartItem)
        }
    }
}

@Composable
fun CheckoutButton(onCheckoutClick: () -> Unit = {}) {
    Button(
        onClick = onCheckoutClick,
        modifier = Modifier.fillMaxWidth().padding(16.dp).semantics {
            contentDescription = "CheckoutButton"
        }
        //modifier = Modifier.fillMaxWidth().padding(16.dp).testTag("CheckoutButton")
    ) {
        Text(text = "Checkout", fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartScreenPreview() {

    ShoppingCartScreen(
        shoppingCartViewModel = ShoppingCartViewModel(),
        navController = NavHostController(LocalContext.current)
    )
}