package com.stephen.phonepe_bootcamp.presentation.home_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stephen.phonepe_bootcamp.domain.model.dummyProducts
import com.stephen.phonepe_bootcamp.presentation.components.ExploreSearchBar
import com.stephen.phonepe_bootcamp.presentation.home_page.components.ProductItemsList

@Composable
fun HomePageScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),

        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            ExploreSearchBar(
                onSearch = {
                    // TODO: Debounce search
                }
            )

            ProductItemsList(
                products = dummyProducts
            )
        }
    }
}