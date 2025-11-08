package com.example.paypals.ui.navigation

sealed class Routes (val route: String) {
     object ScreenGroups: Routes("GroupScreen")
     object ScreenPays: Routes("ScreenPays")
     object UserScreen: Routes("UserScreen")

}