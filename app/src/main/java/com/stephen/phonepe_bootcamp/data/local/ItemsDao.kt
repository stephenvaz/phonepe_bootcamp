package com.stephen.phonepe_bootcamp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stephen.phonepe_bootcamp.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    // combination of update and insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM Item ORDER BY id DESC")
    fun getItemsLocal(): Flow<List<Item>>
}