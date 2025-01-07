package com.stephen.phonepe_bootcamp.data.repository

import android.util.Log
import com.stephen.phonepe_bootcamp.data.local.ItemsDao
import com.stephen.phonepe_bootcamp.data.remote.ItemsApi
import com.stephen.phonepe_bootcamp.data.remote.dto.toItem
import com.stephen.phonepe_bootcamp.domain.model.Item
import com.stephen.phonepe_bootcamp.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsApi: ItemsApi,
    private val itemsDao: ItemsDao
) : ItemsRepository {
    override suspend fun getItems(): List<Item> {
        return itemsApi.getItems().data.items.map { it.toItem() }
    }

    override suspend fun getItemsLocal(): Flow<List<Item>> {
        val items = itemsDao.getItemsLocal()
//        log the items
        return items
    }
}