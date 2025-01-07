package com.stephen.phonepe_bootcamp.presentation.explore_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stephen.phonepe_bootcamp.domain.model.dummyItems
import com.stephen.phonepe_bootcamp.presentation.common.MainViewModel
import com.stephen.phonepe_bootcamp.presentation.common.components.ExploreSearchBar
import com.stephen.phonepe_bootcamp.presentation.common.hideKeyboardOnOutsideClick
import com.stephen.phonepe_bootcamp.presentation.explore_page.components.ProductGridList
import com.stephen.phonepe_bootcamp.presentation.explore_page.components.ProductGridListShimmer
import com.stephen.phonepe_bootcamp.presentation.home_page.components.ProductItemsListShimmer

@Composable
fun ExplorePageScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel<MainViewModel>()
) {
    val state = viewModel.state
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .hideKeyboardOnOutsideClick(),
    ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ExploreSearchBar(
                    onSearch = { query -> viewModel.searchItems(query) },
                    searchQuery = state.searchQuery,
                    priceRange = state.priceRange,
                    selectedShippingOptions = state.selectedShippingOptions,
                    onPriceRangeChange = { range -> viewModel.updatePriceRange(range) },
                    onShippingOptionToggle = { option -> viewModel.toggleShippingOption(option) },
                    selectedPriceRange = state.selectedPriceRange,
                    shippingOptions = state.shippingOptions,
                )
                if (state.error.isNotBlank()) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.error)
                            .padding(10.dp)

                    ) {
                        Text(
                            text = state.error,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onError,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        )
                    }
                }
                if (state.isLoading) {
                    ProductGridListShimmer()
                }
                ProductGridList(items = state.items)
            }
    }
}