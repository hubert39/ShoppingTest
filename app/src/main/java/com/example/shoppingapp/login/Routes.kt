package com.example.shoppingapp.login

import androidx.navigation.NavController

sealed class Routes(val route: String) {
    data object MainRoute : Routes("mainRoutes"){
        data object Login : Routes("${MainRoute.route}/login") {
            fun NavController.toLogin() = navigate("${MainRoute.route}/login")
        }
        data object Home : Routes("${MainRoute.route}/home") {
            fun NavController.toHome() = navigate("${MainRoute.route}/home")
        }
        data object ShoppingCart : Routes("${MainRoute.route}/shoppingCart") {
            fun NavController.toShoppingCart() = navigate("${MainRoute.route}/shoppingCart")
        }
        data object CheckOutInfo : Routes("${MainRoute.route}/checkOutInfo") {
            fun NavController.toCheckOutInfo() = navigate("${MainRoute.route}/checkOutInfo")
        }
        data object CheckOutOverview : Routes("${MainRoute.route}/checkOutOverview") {
            fun NavController.toCheckOutOverview() = navigate("${MainRoute.route}/checkOutOverview")
        }
        data object CheckOutComplete : Routes("${MainRoute.route}/checkOutComplete") {
            fun NavController.toCheckOutComplete() = navigate("${MainRoute.route}/checkOutComplete")
        }
    }
}