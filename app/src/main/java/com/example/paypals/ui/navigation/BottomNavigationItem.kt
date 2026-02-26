package com.example.paypals.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey


data class BottomNavigationItem(
     val label: String = "",
     val icon: ImageVector = Icons.Filled.Home,
     val key: NavKey = ScreenGroups
) {
     fun bottomNavigationItems() = listOf(
          BottomNavigationItem("Grupos", Icons.Filled.Groups, ScreenGroups),
          BottomNavigationItem("Pagos",  Icons.Filled.Payment, ScreenPays),
          BottomNavigationItem("Usuarios", Icons.Filled.Person, UserScreen),
     )
}