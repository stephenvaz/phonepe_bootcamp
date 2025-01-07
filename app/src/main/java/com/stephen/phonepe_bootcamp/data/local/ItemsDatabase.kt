package com.stephen.phonepe_bootcamp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stephen.phonepe_bootcamp.domain.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract val itemsDao: ItemsDao
}