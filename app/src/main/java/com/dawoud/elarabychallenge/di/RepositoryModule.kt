package com.dawoud.androidengineerchallenge.di


import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.data.cache.dao.PopularNewsDao
import com.dawoud.elarabychallenge.data.network.api.NewsApi
import com.dawoud.elarabychallenge.data.repository.HomePageRepository
import com.dawoud.elarabychallenge.data.repository.SearchPageRepository
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import com.dawoud.elarabychallenge.domain.repository.SearchPageGetAway
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
        NewsApi: NewsApi,
        popularNewsDao:PopularNewsDao,
        countryNewsDao: CountryNewsDao
    ): HomePageGetAway {
        return HomePageRepository(NewsApi,countryNewsDao,popularNewsDao)
    }
    @Singleton
    @Provides
    fun provideSearchPageRepository(
        NewsApi: NewsApi,
    ): SearchPageGetAway {
        return SearchPageRepository(NewsApi)
    }
}