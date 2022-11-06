package ru.ivos.cryptocoin.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.ivos.cryptocoin.data.database.AppDatabase
import ru.ivos.cryptocoin.data.database.CoinInfoDao
import ru.ivos.cryptocoin.data.network.ApiFactory
import ru.ivos.cryptocoin.data.network.ApiService
import ru.ivos.cryptocoin.data.repository.CoinRepositoryImpl
import ru.ivos.cryptocoin.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}