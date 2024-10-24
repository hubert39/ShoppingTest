package com.example.shoppingapp.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shoppingapp.login.Routes.MainRoute.ShoppingCart.toShoppingCart

@Composable
fun ProductsScreen(navController: NavController) {
    Column {
        ProductsList(productList, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsList(products: List<Product>, navController: NavController) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Products",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.semantics {
                            contentDescription = "Products"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.toShoppingCart() }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) {
        innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(products) { product ->
                ProductCard(product, navController) {
                    navController.navigate("productDetails/${it.productId}")
                    //navController.toProduct()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit = {}) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                modifier = Modifier.semantics {
                    contentDescription = title
                }
            )
        },
        navigationIcon = {
            IconButton(onClick = { iconClickAction.invoke()}) {
                Icon(imageVector = imageVector, contentDescription = "Close")
            }
        }
    )
}

@Composable
fun ProductCard(product: Product, navController: NavController, clickAction: (Product) -> Unit)  {
    Card() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight(align = Alignment.Top)
                .clickable { clickAction(product) }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductPicture(product)
                ProductContent(product)
                AddToCartButton (
                    onAddToCartClick = {
                        navController.toShoppingCart()
                    }
                )
            }
        }
    }
}

@Composable
fun ProductPicture(product: Product) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = painterResource(id = product.productImageId),
            contentDescription = product.productName,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProductContent(product: Product) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
    ) {
        Text(text = product.productName)
        Text(text = product.productDescription)
        Text(text = product.productPrice.toString())
    }
}

@Composable
fun AddToCartButton(onAddToCartClick: () -> Unit) {
    Button(
        onClick = onAddToCartClick,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp).testTag("AddToCartButton")
    ) {
        Text(text = "Add to Cart", fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsHomeScreenPreview() {
    ProductsScreen(
        navController = NavHostController(LocalContext.current)
    )
}