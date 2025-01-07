package com.stephen.phonepe_bootcamp.domain.use_case

import com.stephen.phonepe_bootcamp.common.Resource
import com.stephen.phonepe_bootcamp.domain.model.Item
import com.stephen.phonepe_bootcamp.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
) {
    operator fun invoke() : Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading())
            val remoteItems = itemsRepository.getItems()

            val localItems = itemsRepository.getItemsLocal().first()

            // Combine remote and local items
            val items = localItems + remoteItems
            emit(Resource.Success(items))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(message = "Couldn't reach our servers. Check your internet connection"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}