package com.stephen.phonepe_bootcamp.presentation.add_page.components

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stephen.phonepe_bootcamp.R
import com.stephen.phonepe_bootcamp.domain.model.Item
import com.stephen.phonepe_bootcamp.presentation.Screens
import com.stephen.phonepe_bootcamp.presentation.bottom_nav.noRippleClickable
import com.stephen.phonepe_bootcamp.presentation.common.MainViewModel
import com.stephen.phonepe_bootcamp.presentation.ui.theme.LightGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.NavGreen
import com.stephen.phonepe_bootcamp.presentation.ui.theme.SearchWhite
import com.stephen.phonepe_bootcamp.presentation.ui.theme.TextGray
import com.stephen.phonepe_bootcamp.presentation.ui.theme.TextGray2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Error

@Composable
fun AddItemForm(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    navController: NavController
    ) {
    val itemName = remember { mutableStateOf("") }
    val itemPrice = remember { mutableStateOf("") }
    val shipping = remember { mutableStateOf("") }
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        Item Name, Item Price
        CustomTextField(
            state = itemName,
            placeholder = "Item Name",
            prefixImage = R.drawable.round_text_fields_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            state = itemPrice,
            placeholder = "Item Price",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            prefixImage = R.drawable.round_currency_rupee_24,
            supportingText = supportingNumericText(itemPrice.value),
            isValid = ::isValidPositiveNumeric

        )
//        Drop Down for Shipping Method
        CustomDropDown(
            state = shipping
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val name = itemName.value.trim()
                val price = itemPrice.value.trim()
                var shippingMethod = shipping.value.trim()
                if (shippingMethod == "None") {
                    shippingMethod = ""
                }
                if (name.isNotEmpty() && price.isNotEmpty()) {
                    val newItem = Item(name = name, price = price.toDouble(), extra = shippingMethod)

                    // coroutine scope to call suspend function
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.upsertItem(newItem)
                        withContext(Dispatchers.Main) {
                            // navigate back to home screen
                            navController.navigate(Screens.ExploreScreen.route) {
                                popUpTo(Screens.AddScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        navController.context,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
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

fun isValidPositiveNumeric(text: String): Boolean {
    if (text.isEmpty()) return true
    return text.matches(Regex("^[1-9]\\d*(\\.\\d+)?$"))
}

@Composable
fun supportingNumericText(text: String): @Composable () -> Unit {
    return {
        if (!isValidPositiveNumeric(text)) {
            Text(
                text = "Please enter a valid number",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
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
    readOnly: Boolean = false,
    isValid: (String) -> Boolean = { true },
    supportingText: @Composable (() -> Unit)? = null

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
            unfocusedContainerColor = colorResource(R.color.text_field),
            unfocusedPlaceholderColor = TextGray2,
            focusedPlaceholderColor = TextGray2,
            disabledTextColor = textFieldColors.focusedTextColor,
            disabledContainerColor = colorResource(R.color.text_field),
            disabledPlaceholderColor = TextGray2,
            disabledIndicatorColor = LightGray,
        ),
        isError = !isValid(state.value),
        supportingText = supportingText,
        // error text will be shown only if isError is true
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
fun CustomDropDown(
    modifier: Modifier = Modifier,
    state: MutableState<String>
) {
    var isExpanded by remember { mutableStateOf(false) }

    val shippingMethods = listOf("Same Day Shipping", "None")

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
    ) {
        CustomTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    isExpanded = !isExpanded }
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
        Spacer(modifier = Modifier.height(4.dp))
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }),
            shape = MaterialTheme.shapes.medium,
            containerColor = colorResource(R.color.text_field),
        ) {
            shippingMethods.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        state.value = label
                        isExpanded = false
                    },
                    text = { Text(text = label) },

                )
            }
        }
    }
}


