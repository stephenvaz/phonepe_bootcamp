package com.stephen.phonepe_bootcamp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stephen.phonepe_bootcamp.common.Constants.BASE_URL
import com.stephen.phonepe_bootcamp.common.Constants.ITEMS_DATABASE_NAME
import com.stephen.phonepe_bootcamp.data.local.ItemsDao
import com.stephen.phonepe_bootcamp.data.local.ItemsDatabase
import com.stephen.phonepe_bootcamp.data.remote.ItemsApi
import com.stephen.phonepe_bootcamp.data.repository.ItemsRepositoryImpl
import com.stephen.phonepe_bootcamp.domain.repository.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun providesItemsApi(): ItemsApi {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ItemsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemsDatabase(
        application: Application
    ): ItemsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ItemsDatabase::class.java,
            name = ITEMS_DATABASE_NAME
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideItemsDao(
        itemsDatabase: ItemsDatabase
    ): ItemsDao {
        return itemsDatabase.itemsDao
    }

    @Provides
    @Singleton
    fun provideItemsRepository(
        itemsApi: ItemsApi,
        itemsDao: ItemsDao
    ): ItemsRepository {
        return ItemsRepositoryImpl(
            itemsApi = itemsApi,
            itemsDao = itemsDao
        )
    }

}