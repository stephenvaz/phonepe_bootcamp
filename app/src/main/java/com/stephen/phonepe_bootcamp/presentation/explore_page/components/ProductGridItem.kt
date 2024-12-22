package com.stephen.phonepe_bootcamp.presentation.explore_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
//import com.stephen.phonepe_bootcamp.domain.model.dummyProducts
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightGray

@Composable
fun ProductGridItem(
    modifier: Modifier = Modifier,
    itemName: String,
    itemPrice: Double,
    shippingMethod: String,
    imageUrl: String? = null
) {
//    var lineCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = LightGray)
                .shadow(
                    elevation = 100.dp,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            // Image
            if (imageUrl != null) {
                // Load Image
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Product Image for $itemName",
                )
            } else {
                // Placeholder Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(color = LightGray)
                )
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        // Product Name
        Text(
            modifier = Modifier
                .padding(end = 8.dp),
            text = itemName,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
//            onTextLayout = { textLayoutResult ->
//                lineCount = textLayoutResult.lineCount
//            }
        )
        // Dynamic Spacer
        Spacer(
            modifier = Modifier.height(
//                when (lineCount) {
//                    1 -> MaterialTheme.typography.bodyMedium.fontSize.value.dp * 2 + 4.dp
//                    2 -> MaterialTheme.typography.bodyMedium.fontSize.value.dp + 4.dp
//                    else -> 0.dp
//                }
                2.dp
            )
        )
        // Product Price
        Text(
            text = "₹${itemPrice.toInt()}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview(
//    showSystemUi = true,
//    showBackground = true
//)
//@Composable
//fun ProductGridItemPreview() {
//    ProductGridItem(
//        itemName = dummyProducts[0].name,
//        itemPrice = dummyProducts[0].price,
//        shippingMethod = dummyProducts[0].shippingMethod,
//        imageUrl = dummyProducts[0].imageUrl
//    )
//}