package com.stephen.phonepe_bootcamp.presentation.home_page.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.stephen.phonepe_bootcamp.presentation.common.shimmerEffect
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.Phonepe_bootcampTheme
import com.stephen.phonepe_bootcamp.presentation.ui.theme.TextGray

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    itemName: String,
    itemPrice: Double,
    shippingMethod: String,
    imageUrl: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = LightGray)
        ) {
            // Image
            if (imageUrl != null) {
                // Load Image
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Product Image for $itemName",
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Product Name
            Text(
                text = itemName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // Add maximum space between the above text and the below row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Product Price
                PriceRow(itemPrice)
                // Shipping
                Text(
                    text = shippingMethod,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
            }
            HorizontalDivider(
                modifier = Modifier,
                color = LightGray

            )
        }
    }
}

@Composable
private fun PriceRow(itemPrice: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "MRP: ", style = MaterialTheme.typography.bodyMedium, color = TextGray)
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "₹$itemPrice", style = MaterialTheme.typography.bodyMedium)
    }
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true,
//)
//@Composable
//fun ProductItemPreview() {
//    ProductItem(
//        itemName = "Product Name",
//        itemPrice = 100.0,
//        shippingMethod = "Same Day Shipping",
//        imageUrl = "https://picsum.photos/200/300"
//    )
//}

@Composable
fun ProductItemShimmer(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = LightGray)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Product Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = LightGray)
                    .shimmerEffect()
            )
            // Add maximum space between the above text and the below row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Product Price
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.269f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = LightGray)
                        .shimmerEffect()
                )
                // Shipping
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = LightGray)
                        .shimmerEffect()
                )
            }
            HorizontalDivider(
                modifier = Modifier,
                color = LightGray

            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ProductItemShimmerPreview() {
    Phonepe_bootcampTheme {
        ProductItemShimmer()
    }
}
