package com.stephen.phonepe_bootcamp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stephen.phonepe_bootcamp.data.local.ItemsDao
import com.stephen.phonepe_bootcamp.domain.model.dummyItems
import com.stephen.phonepe_bootcamp.presentation.Screens
import com.stephen.phonepe_bootcamp.presentation.bottom_nav.BottomNavBar
import com.stephen.phonepe_bootcamp.presentation.common.MainViewModel
import com.stephen.phonepe_bootcamp.presentation.explore_page.ExplorePageScreen
import com.stephen.phonepe_bootcamp.presentation.home_page.AddPageScreen
import com.stephen.phonepe_bootcamp.presentation.home_page.HomePageScreen
import com.stephen.phonepe_bootcamp.presentation.ui.theme.Phonepe_bootcampTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // manually adding data into the db
//    @Inject
//    lateinit var dao: ItemsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        lifecycleScope.launch {
//            dao.upsert(
//                dummyItems[3]
//            )
//        }

        setContent {
            Phonepe_bootcampTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = hiltViewModel<MainViewModel>()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        startDestination = Screens.HomeScreen.route,
                    ) {
                        composable(Screens.HomeScreen.route) {
                            HomePageScreen(
                                viewModel = mainViewModel
                            )
                        }
                        composable(Screens.ExploreScreen.route) {
                            ExplorePageScreen(
                                viewModel = mainViewModel
                            )
                        }
                        composable(Screens.AddScreen.route) {
                            AddPageScreen(
                                viewModel = mainViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}