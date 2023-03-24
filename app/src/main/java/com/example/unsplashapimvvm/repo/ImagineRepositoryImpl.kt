package com.example.unsplashapimvvm.repo

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.unsplashapimvvm.apiservices.UnsplashApiService
import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ImagineRepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService
) : ImagineRepository {


    override suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<DataState<List<PhotoModel>>> {
        return flow {
            apiService.loadPhotos(pageNumber, pageSize, orderBy).apply {
                Log.i("TAG", "loadPhotos: ${this.body()}")

                if (this.isSuccessful) {

                    emit(DataState.Success(body()!!))


                } else {
                    emit(DataState.Error(message()))


                }

            }
        }
    }

    override suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<DataState<List<PhotoModel>>> {
        return flow {
            apiService.searchPhotos(query, pageNumber, pageSize).apply {
            }
        }
    }
}
