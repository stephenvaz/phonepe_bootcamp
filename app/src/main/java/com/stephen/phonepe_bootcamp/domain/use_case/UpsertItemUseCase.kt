package com.stephen.phonepe_bootcamp.domain.use_case

import com.stephen.phonepe_bootcamp.data.local.ItemsDao
import com.stephen.phonepe_bootcamp.domain.model.Item
import javax.inject.Inject

class UpsertItemUseCase @Inject constructor(
    private val itemsDao: ItemsDao
) {
    suspend operator fun invoke(item: Item){
        itemsDao.upsert(item)
    }
}