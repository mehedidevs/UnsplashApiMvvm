package com.example.unsplashapimvvm.di

import com.example.unsplashapimvvm.apiservices.UnsplashApiService
import com.example.unsplashapimvvm.repo.ImagineRepository
import com.example.unsplashapimvvm.repo.ImagineRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideImagineRepository(apiService: UnsplashApiService): ImagineRepository {
        return ImagineRepositoryImpl(apiService)
    }
}
