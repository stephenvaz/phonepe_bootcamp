package com.stephen.phonepe_bootcamp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.R
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightBlue

@Composable
fun TopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
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
    // take in a composable here
        content()
    }
}