package com.example.paypals.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
     val label: String = "",
     val icon: ImageVector = Icons.Filled.Home,
     val route: String = ""
) {

     fun bottomNavigationItems(): List<BottomNavigationItem> {
          return listOf(
               BottomNavigationItem(
                    label = "Grupos",
                    icon = Icons.Filled.Groups,
                    route = Routes.ScreenGroups.route
               ),
               BottomNavigationItem(
                    label = "Pagos",
                    icon = Icons.Filled.Payment,
                    route = Routes.ScreenPays.route
               ),
               BottomNavigationItem(
                    label = "Usuarios",
                    icon = Icons.Filled.Person,
                    route = Routes.UserScreen.route
               ),
          )
     }
}