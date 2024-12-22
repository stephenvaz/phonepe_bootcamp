package com.stephen.phonepe_bootcamp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightBlue

@Composable
fun TopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .background(LightBlue)
            .padding(22.dp)
            .statusBarsPadding()
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
    ) {
    // take in a composables here
        content()
    }
}