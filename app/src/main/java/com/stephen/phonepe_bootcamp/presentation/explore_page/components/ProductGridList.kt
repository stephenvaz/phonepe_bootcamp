package com.stephen.phonepe_bootcamp.presentation.explore_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.domain.model.Product

@Composable
fun ProductGridList(modifier: Modifier = Modifier, products: List<Product>) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 8.dp,
            top = 32.dp,
            bottom = 8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(products.size) { index ->
                ProductGridItem(
                    itemName = products[index].name,
                    itemPrice = products[index].price,
                    shippingMethod = products[index].shippingMethod,
                    imageUrl = products[index].imageUrl
                )
            }
        }

    )
}