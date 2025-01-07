package com.stephen.phonepe_bootcamp.presentation.home_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stephen.phonepe_bootcamp.presentation.add_page.components.AddBar
import com.stephen.phonepe_bootcamp.presentation.add_page.components.AddItemForm
import com.stephen.phonepe_bootcamp.presentation.common.MainViewModel
import com.stephen.phonepe_bootcamp.presentation.common.hideKeyboardOnOutsideClick

@Composable
fun AddPageScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .hideKeyboardOnOutsideClick(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            AddBar()

            AddItemForm(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}