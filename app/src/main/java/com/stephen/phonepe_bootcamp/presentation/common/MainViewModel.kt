package com.stephen.phonepe_bootcamp.presentation.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephen.phonepe_bootcamp.common.Resource
import com.stephen.phonepe_bootcamp.domain.model.Item
import com.stephen.phonepe_bootcamp.domain.use_case.GetItemsUseCase
import com.stephen.phonepe_bootcamp.domain.use_case.UpsertItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val upsertItemUseCase: UpsertItemUseCase
) : ViewModel() {

    var state by mutableStateOf(ItemsState())
        private set
    private var allItems: List<Item> = emptyList()


    init {
        getItems()
    }

    private fun getItems() {
        state = state.copy(isLoading = true)
        getItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    allItems = result.data ?: emptyList()
                    calculateFilterOptions()
                    applyFiltersAndSearch()
                    state = state.copy(isLoading = false)
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    suspend fun upsertItem(item: Item) {
        upsertItemUseCase(item)
        getItems()
    }

    private fun calculateFilterOptions() {
        if (allItems.isNotEmpty()) {
            val prices = allItems.map { it.price.toFloat() }
            val priceRange = prices.minOrNull()!!..prices.maxOrNull()!!

            state = state.copy(
                priceRange = priceRange,
                selectedPriceRange = priceRange,
                shippingOptions = allItems.map { it.extra }.toSet()
            )
        }
    }

    fun updatePriceRange(range: ClosedFloatingPointRange<Float>) {
        state = state.copy(selectedPriceRange = range)
        applyFiltersAndSearch()
    }

    fun toggleShippingOption(option: String) {
        val updatedOptions = if (state.selectedShippingOptions.contains(option)) {
            state.selectedShippingOptions - option
        } else {
            state.selectedShippingOptions + option
        }
        state = state.copy(selectedShippingOptions = updatedOptions)
        applyFiltersAndSearch()
    }

    fun searchItems(query: String) {
        state = state.copy(searchQuery = query)
        applyFiltersAndSearch()
    }

    private fun applyFiltersAndSearch() {
        val query = state.searchQuery
        state = state.copy(
            items = allItems.filter { item ->
                item.price in state.selectedPriceRange &&
                        (state.selectedShippingOptions.isEmpty() || state.selectedShippingOptions.contains(item.extra)) &&
                        (query.isEmpty() || item.name.contains(query, ignoreCase = true))
            }
        )
    }
}

