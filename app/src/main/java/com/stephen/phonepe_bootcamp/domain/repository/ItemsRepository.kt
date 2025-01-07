package com.stephen.phonepe_bootcamp.domain.repository

import com.stephen.phonepe_bootcamp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getItems(): List<Item>

    suspend fun getItemsLocal(): Flow<List<Item>>
}