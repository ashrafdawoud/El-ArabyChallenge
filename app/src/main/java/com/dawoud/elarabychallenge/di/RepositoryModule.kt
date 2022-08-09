package com.dawoud.androidengineerchallenge.di


import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.data.cache.dao.PopularNewsDao
import com.dawoud.elarabychallenge.data.network.api.newsApi
import com.dawoud.elarabychallenge.data.repository.HomePageRepository
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomePageRepository(
        newsApi: newsApi,
        popularNewsDao:PopularNewsDao,
        countryNewsDao: CountryNewsDao
    ): HomePageGetAway {
        return HomePageRepository(newsApi,countryNewsDao,popularNewsDao)
    }
}