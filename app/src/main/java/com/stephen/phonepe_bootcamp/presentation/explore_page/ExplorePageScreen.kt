package com.stephen.phonepe_bootcamp.presentation.explore_page

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
import com.stephen.phonepe_bootcamp.domain.model.dummyProducts
import com.stephen.phonepe_bootcamp.presentation.components.ExploreSearchBar
import com.stephen.phonepe_bootcamp.presentation.explore_page.components.ProductGridList

@Composable
fun ExplorePageScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ExploreSearchBar{}
            ProductGridList(products = dummyProducts)
        }
    }
}