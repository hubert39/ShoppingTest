package com.example.shoppingapp.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppingapp.products.ProductDetailScreen
import com.example.shoppingapp.products.ProductsScreen
import com.example.shoppingapp.shoppingcart.CheckOutCompleteScreen
import com.example.shoppingapp.shoppingcart.CheckOutInfoScreen
import com.example.shoppingapp.shoppingcart.CheckOutOverviewScreen
import com.example.shoppingapp.shoppingcart.ShoppingCartViewModel
import com.example.shoppingapp.shoppingcart.ShoppingCartScreen

const val PRODUCT_ID = "productId"

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MainRoute.Login.route) {
        composable(route = Routes.MainRoute.Login.route) { LoginScreen(navController) }
        composable(route = Routes.MainRoute.Home.route) { ProductsScreen(navController) }
        composable(route = Routes.MainRoute.ShoppingCart.route) {
            val shoppingCartViewModel = ViewModelProvider(navController.currentBackStackEntry!!).get(ShoppingCartViewModel::class.java)
            ShoppingCartScreen(shoppingCartViewModel, navController)
        }
        //composable(route = Routes.MainRoute.Product.route) { ProductsScreen(navController) }
        composable(route = Routes.MainRoute.CheckOutInfo.route) { CheckOutInfoScreen(navController) }
        composable(route = Routes.MainRoute.CheckOutOverview.route) { CheckOutOverviewScreen(navController) }
        composable(route = Routes.MainRoute.CheckOutComplete.route) { CheckOutCompleteScreen(navController) }
        composable(
            route = "productDetails/{$PRODUCT_ID}",
            arguments = listOf(navArgument(PRODUCT_ID) { type = NavType.IntType })
        ) {
            ProductDetailScreen(
                productId = it.arguments?.getInt(PRODUCT_ID) ?: 1,
                navController = navController
            )
        }
    }
}