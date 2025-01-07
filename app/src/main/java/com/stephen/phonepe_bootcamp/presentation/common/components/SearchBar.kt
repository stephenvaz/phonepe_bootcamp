package com.stephen.phonepe_bootcamp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.R
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightBlue
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen
import com.stephen.phonepe_bootcamp.presentation.ui.theme.SearchWhite
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    searchQuery: String,
    priceRange: ClosedFloatingPointRange<Float>,
    selectedPriceRange: ClosedFloatingPointRange<Float>,
    shippingOptions: Set<String>,
    selectedShippingOptions: Set<String>,
    onPriceRangeChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onShippingOptionToggle: (String) -> Unit
) {
//    TODO: this component will be used on both the home page and the explore page, so i will pass a lambda to the component to handle the search functionality
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    TopBar ()
    {   
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Explore", fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleLarge.fontSize, modifier = modifier.alignByBaseline())
                Text(
                    "Filter",
                    color = NavGreen,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    modifier = modifier.alignByBaseline()
                        .clip(
                            MaterialTheme.shapes.medium
                        )
                        .clickable {
                            showBottomSheet = true
                        }
                        .padding(horizontal = 4.dp)
                )
            }

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .scale(scaleX = 1.0f ,scaleY = 0.95f),
                colors = SearchBarDefaults.colors(
//                    containerColor = SearchWhite,
                    containerColor = colorResource(R.color.search_bar),
                    dividerColor = Color.DarkGray
                ),
                windowInsets = WindowInsets(
                        top = 2.dp,
                ),
                inputField = {
                    SearchBarDefaults.InputField(
                        enabled = true,
                        expanded = false,
                        onExpandedChange = { },
                        query = searchQuery,
                        onQueryChange = { query ->
                                onSearch(query)
                        },

                        colors = SearchBarDefaults.inputFieldColors().copy(
                            unfocusedTextColor = Color.LightGray,
                        ),
                        placeholder = { Text("Search",
                            textAlign = TextAlign.Center
                        ) },
                        onSearch = {
                            onSearch(searchQuery)
                        }
                    )
                },
                onExpandedChange = {  },
                expanded = false,
            ) { }
        }
        if (showBottomSheet) {
            FilterModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false },
                priceRange = priceRange,
                selectedShippingOptions = selectedShippingOptions,
                onPriceRangeChange = onPriceRangeChange,
                onShippingOptionToggle = onShippingOptionToggle,
                selectedPriceRange = selectedPriceRange,
                shippingOptions = shippingOptions,
            )
        }
    }
}