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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModelBak @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val upsertItemUseCase: UpsertItemUseCase
) : ViewModel() {

    var state by mutableStateOf(ItemsState())
        private set
    private var allItems: List<Item> = emptyList()
    private val searchQueryFlow = MutableStateFlow("")

    init {
        getItems()
        observeSearchQuery()
    }

    private fun getItems() {
        getItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    allItems = result.data ?: emptyList()
                    state = ItemsState(items = allItems)
                }

                is Resource.Error -> {
                    state = ItemsState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state = ItemsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun upsertItem(item: Item) {
        upsertItemUseCase(item)
        // Refresh the list of items
        getItems()
    }

    private fun setSearchQuery(query: String) {
        state = state.copy(searchQuery = query)
    }

    fun onSearchQueryChanged(query: String) {
        Log.d("MainViewModel", "onSearchQueryChanged: $query")
        searchQueryFlow.value = query
        setSearchQuery(query)
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        searchQueryFlow
//            .debounce(500L)
//            .distinctUntilChanged()
            .onEach { query ->
                filterItems(query)
            }
            .launchIn(viewModelScope)
    }

    private fun filterItems(query: String) {
        state = if (query.isEmpty()) {
            ItemsState(items = allItems)
        } else {
            val filteredItems = allItems.filter {
                it.name.contains(query, ignoreCase = true)
            }
            ItemsState(items = filteredItems)
        }
    }
    fun searchItems(query: String) {
        state = if (query.isEmpty()) {
            ItemsState(items = allItems)
        } else {
            val filteredItems = allItems.filter {
                it.name.contains(query, ignoreCase = true)
            }
            ItemsState(items = filteredItems, searchQuery = query)
        }
    }
}
