package com.example.shoppingapp.shoppingcart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingapp.login.Routes.MainRoute.CheckOutComplete.toCheckOutComplete
import com.example.shoppingapp.products.AppBar
import com.example.shoppingapp.products.Product
import com.example.shoppingapp.products.productList

@Composable
fun CheckOutOverviewScreen(navController: NavController) {
    Column() {
        CheckOutCartList(productList.subList(0,2), navController)
    }
}

@Composable
fun CheckOutCartList(checkOutItems: List<Product>, navController: NavController) {
    Scaffold (
        topBar = {
            AppBar(title = "Check Out Overview", imageVector = Icons.Filled.Close) {
                navController.popBackStack()
            }
        }
    ){
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(checkOutItems) { checkOutItem ->
                    ShoppingCartCard(checkOutItem) {
                        //navController.popBackStack()
                    }
                }
            }
            FinishButton(
                onFinishClick = {
                    navController.toCheckOutComplete()
                }
            )
        }

    }

}

@Composable
fun FinishButton(onFinishClick: () -> Unit = {}) {
    Button(
        onClick = onFinishClick,
        modifier = Modifier.fillMaxWidth().padding(16.dp).semantics {
            contentDescription = "FinishButton"
        }
    ) {
        Text(text = "Finish", fontSize = 12.sp)
    }
}


