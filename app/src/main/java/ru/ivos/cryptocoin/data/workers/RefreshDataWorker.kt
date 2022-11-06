package ru.ivos.cryptocoin.data.workers

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.delay
import ru.ivos.cryptocoin.data.database.AppDatabase
import ru.ivos.cryptocoin.data.database.CoinInfoDao
import ru.ivos.cryptocoin.data.mapper.CoinMapper
import ru.ivos.cryptocoin.data.mapper.DbModelMapper
import ru.ivos.cryptocoin.data.mapper.JsonMapper
import ru.ivos.cryptocoin.data.mapper.NamesMapper
import ru.ivos.cryptocoin.data.network.ApiFactory
import ru.ivos.cryptocoin.data.network.ApiService
import ru.ivos.cryptocoin.di.ChildWorkerFactory
import javax.inject.Inject

class RefreshDataWorker (
    context: Context,
    private val dao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper,
    workerParameters: WorkerParameters)
    : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true){
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val json = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonToCoinInfo(json)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                dao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    class Factory @Inject constructor(
        private val dao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinMapper,
    ): ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context, dao, apiService, mapper, workerParameters
            )
        }
    }

    companion object {
        const val NAME = "worker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}