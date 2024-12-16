package com.stephen.phonepe_bootcamp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightBlue
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen
import com.stephen.phonepe_bootcamp.presentation.ui.theme.SearchWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreSearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
//    TODO: this component will be used on both the home page and the explore page, so i will pass a lambda to the component to handle the search functionality
    var searchQuery by remember { mutableStateOf("") }
    Box (
        modifier = modifier
            .background(LightBlue)
            .padding(22.dp)
            .statusBarsPadding()
            .fillMaxWidth()
    ) {
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
                Text("Filter", color = NavGreen, fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.titleSmall.fontSize,     modifier = modifier.alignByBaseline())
            }

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
//                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .scale(scaleX = 1.0f ,scaleY = 0.95f),
                colors = SearchBarDefaults.colors(
                    containerColor = SearchWhite,
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
                        onQueryChange = { searchQuery = it },
                        query = searchQuery,
                        colors = SearchBarDefaults.inputFieldColors().copy(
                            unfocusedTextColor = Color.LightGray,
                        ),
                        placeholder = { Text("Search",
                            textAlign = TextAlign.Center
                        ) },
                        onSearch = {
//                            TODO: call the lambda here
                            onSearch(searchQuery)
                        }
                    )
                },
                onExpandedChange = {  },
                expanded = false,
            ) { }
        }
    }
}