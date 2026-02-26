package com.example.paypals.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.paypals.MainTopAppBar
import com.example.paypals.ui.screen.groups.GroupScreen
import com.example.paypals.ui.screen.pay.PaymentScreen
import com.example.paypals.ui.screen.users.UserScreen
import com.example.paypals.ui.screen.users.UserViewModel

@Composable
fun MainNavigationScreen() {
     val backStack = rememberNavBackStack(ScreenGroups)

     val currentKey = backStack.lastOrNull()

     Scaffold(
          modifier = Modifier.fillMaxSize(),
          bottomBar = {
               NavigationBar {
                    BottomNavigationItem().bottomNavigationItems()
                         .forEach { item ->
                              NavigationBarItem(
                                   selected = currentKey == item.key,
                                   label = { Text(item.label) },
                                   icon = { Icon(item.icon, contentDescription = item.label) },
                                   onClick = {
                                        backStack.clear()
                                        backStack.add(item.key)
                                   }
                              )
                         }
               }
          }
     ) { paddingValues ->
          NavDisplay(
               backStack = backStack,
               modifier = Modifier.padding(paddingValues),
               entryProvider = entryProvider {
                    entry<ScreenGroups> { GroupScreen() }
                    entry<ScreenPays>  { PaymentScreen() }
                    entry<UserScreen>  { UserScreen() }
               }
          )
     }
}