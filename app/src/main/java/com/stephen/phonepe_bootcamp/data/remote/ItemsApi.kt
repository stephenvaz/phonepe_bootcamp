package com.stephen.phonepe_bootcamp.data.remote

import com.stephen.phonepe_bootcamp.data.remote.dto.ItemsDto
import retrofit2.http.GET

interface ItemsApi {
    @GET("/")
    suspend fun getItems(): ItemsDto
}