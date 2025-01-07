package com.stephen.phonepe_bootcamp.presentation.add_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.R
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightBlue
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen

@Composable
fun AddBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                colorResource(R.color.top_bar)
            )
            .padding(22.dp)
            .statusBarsPadding()
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Add Item", fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            Text("Add", color = NavGreen, fontWeight = FontWeight.SemiBold, fontSize = MaterialTheme.typography.titleSmall.fontSize)
        }
    }
}