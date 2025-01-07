package com.stephen.phonepe_bootcamp.presentation.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.stephen.phonepe_bootcamp.presentation.bottom_nav.noRippleClickable

fun Modifier.hideKeyboardOnOutsideClick(): Modifier = composed {
    val controller = LocalSoftwareKeyboardController.current
    this then Modifier.noRippleClickable {
        controller?.hide()
    }
}