package com.stephen.phonepe_bootcamp.presentation.common.components

import android.provider.CalendarContract.Colors
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stephen.phonepe_bootcamp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterModalBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    priceRange: ClosedFloatingPointRange<Float>,
    selectedPriceRange: ClosedFloatingPointRange<Float>,
    shippingOptions: Set<String>,
    selectedShippingOptions: Set<String>,
    onPriceRangeChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onShippingOptionToggle: (String) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Filters",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Price Range Slider
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterSheetIcon(R.drawable.round_currency_rupee_24)
                Text(text = "Price", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            RangeSlider(
                value = selectedPriceRange,
                onValueChange = { onPriceRangeChange(it) },
                valueRange = priceRange,
//                steps = 10
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Selected Range: ${selectedPriceRange.start.toInt()} - ${selectedPriceRange.endInclusive.toInt()}"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Shipping Options
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterSheetIcon(
                    R.drawable.round_shopping_cart_checkout_24
                )
                Text(text = "Shipping Options", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            shippingOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onShippingOptionToggle(option) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedShippingOptions.contains(option),
                        onCheckedChange = { onShippingOptionToggle(option) }
                    )
                    Text(
                        text = option.ifEmpty { "None" },
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterSheetIcon(
    @DrawableRes icon: Int,
) {
    Image(
        painter = painterResource(icon),
        colorFilter = ColorFilter.tint(colorResource(R.color.icon_tint)),
        contentDescription = null,
        modifier = Modifier.size(18.dp)
    )
}
