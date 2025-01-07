package com.stephen.phonepe_bootcamp.data.remote.dto

import com.stephen.phonepe_bootcamp.domain.model.Item


data class ItemsDto(
    val data: Data,
    val status: String
)

//fun ItemsDto.toItems(): List<Item> {
//    return data.items.map {
//            Item(
//                extra = it.extra,
//                name = it.name,
//                price = it.price.toDouble()
//            )
//        }
//}

