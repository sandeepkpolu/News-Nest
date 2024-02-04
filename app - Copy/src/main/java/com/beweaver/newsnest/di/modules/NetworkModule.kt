package com.beweaver.newsnest.di.modules

import com.beweaver.newsnest.data.BaseNewsRepository
import com.beweaver.newsnest.data.NewsRepository
import com.beweaver.newsnest.di.api.ApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() :Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://timesofindia.indiatimes.com/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService): BaseNewsRepository {
        return NewsRepository(apiService)
    }
}