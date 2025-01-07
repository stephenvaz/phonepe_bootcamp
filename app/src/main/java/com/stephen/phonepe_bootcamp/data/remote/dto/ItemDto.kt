package com.stephen.phonepe_bootcamp.data.remote.dto

import com.stephen.phonepe_bootcamp.domain.model.Item


data class ItemDto(
    val extra: String,
    val name: String,
    val price: String
)

fun ItemDto.toItem(): Item {
    return Item(
        extra = extra,
        name = name,
        price = price.toDouble()
    )
}