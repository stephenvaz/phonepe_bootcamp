package com.stephen.phonepe_bootcamp.presentation.add_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.stephen.phonepe_bootcamp.R
import com.stephen.phonepe_bootcamp.presentation.bottom_nav.noRippleClickable
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen
import com.stephen.phonepe_bootcamp.presentation.ui.theme.SearchWhite
import com.stephen.phonepe_bootcamp.presentation.ui.theme.TextGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.TextGray2

@Composable
fun AddItemForm(modifier: Modifier = Modifier) {
    val itemName = remember { mutableStateOf("") }
    val itemPrice = remember { mutableStateOf("") }
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        Item Name, Item Price
        CustomTextField(
            state = itemName,
            placeholder = "Item Name",
            prefixImage = R.drawable.round_text_fields_24
        )
        CustomTextField(
            state = itemPrice,
            placeholder = "Item Price",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            prefixImage = R.drawable.round_currency_rupee_24

        )
//        Drop Down for Shipping Method
        CustomDropDown()

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = NavGreen
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
        ) {
            Text(text = "Submit", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = SearchWhite)
        }
    }
}

@Composable
fun CustomTextField(
    state: MutableState<String>,
    modifier: Modifier = Modifier,
    placeholder: String, keyboardOptions:
    KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    prefixImage: Int? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false

) {
    val textFieldColors = OutlinedTextFieldDefaults.colors()
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        shape = MaterialTheme.shapes.extraLarge,
        singleLine = true,
        enabled = !readOnly,
        colors = textFieldColors.copy(
            unfocusedIndicatorColor = LightGray,
            unfocusedContainerColor = SearchWhite,
            unfocusedPlaceholderColor = TextGray2,
            focusedPlaceholderColor = TextGray2,
//           even if field is disabled, the text color should be the same as normal text color
            disabledTextColor = textFieldColors.focusedTextColor

        ),

        prefix = {
            if (prefixImage != null)
            Image(
                painter = painterResource(id = prefixImage),
                contentDescription = "",
                colorFilter = ColorFilter.tint(TextGray),
            )
        },
        trailingIcon = {
            if (trailingIcon != null)
            trailingIcon()
        },
        modifier = modifier
            .fillMaxWidth(0.85f),
        placeholder = { Text(placeholder, fontWeight = FontWeight.SemiBold) },
        keyboardOptions = keyboardOptions,
        readOnly = readOnly,
    )
}

@Composable
fun CustomDropDown(modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val shippingMethods = listOf("Same Day Shipping", "None")

    val shippingState = remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {

        // Create an Outlined Text Field
        // with icon and not expanded
        CustomTextField(
            state = shippingState,
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    isExpanded = true }
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },

            placeholder = "Shipping Method",
            readOnly = true,
            prefixImage = R.drawable.round_shopping_cart_checkout_24,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = TextGray
                )
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        Spacer(modifier = Modifier.height(8.dp))
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ,
            shape = MaterialTheme.shapes.medium,
            containerColor = SearchWhite,
        ) {
            shippingMethods.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        shippingState.value = label
                        isExpanded = false
                    },
                    text = { Text(text = label) },

                )
            }
        }
    }
}


