package com.stephen.phonepe_bootcamp.presentation.home_page.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.domain.model.Product

@Composable
fun ProductItemsList(modifier: Modifier = Modifier, products: List<Product>) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
        )
    ) {
        items(products) { item ->
            ProductItem(
                itemName = item.name,
                itemPrice = item.price,
                shippingMethod = item.shippingMethod,
                imageUrl = item.imageUrl
            )
        }
    }
}


