package com.example.unsplashapimvvm.di

import android.app.Application
import com.example.unsplashapimvvm.apiservices.UnsplashApiService
import com.example.unsplashapimvvm.repo.ImagineRepository
import com.example.unsplashapimvvm.repo.ImagineRepositoryImpl
import com.example.unsplashapimvvm.utils.StringUtils

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
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(
        apiService: UnsplashApiService,
        stringUtils: StringUtils
    ): ImagineRepository {
        return ImagineRepositoryImpl(apiService, stringUtils)
    }
}
