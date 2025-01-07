package com.stephen.phonepe_bootcamp.presentation.common

import com.stephen.phonepe_bootcamp.domain.model.Item

data class ItemsState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String = "",
    val searchQuery: String = "",
    val priceRange: ClosedFloatingPointRange<Float> = 0f..0f,
    val selectedPriceRange: ClosedFloatingPointRange<Float> = 0f..0f,
    val shippingOptions: Set<String> = emptySet(),
    val selectedShippingOptions: Set<String> = emptySet()
)

