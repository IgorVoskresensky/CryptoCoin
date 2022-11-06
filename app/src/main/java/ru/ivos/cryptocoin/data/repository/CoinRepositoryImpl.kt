package ru.ivos.cryptocoin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.ivos.cryptocoin.data.database.AppDatabase
import ru.ivos.cryptocoin.data.database.CoinInfoDao
import ru.ivos.cryptocoin.data.mapper.CoinMapper
import ru.ivos.cryptocoin.data.mapper.DbModelMapper
import ru.ivos.cryptocoin.data.workers.RefreshDataWorker
import ru.ivos.cryptocoin.domain.CoinInfo
import ru.ivos.cryptocoin.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val dao: CoinInfoDao,
    private val mapper: CoinMapper
    ) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(dao.getPriceList()) {
            it.map { entity ->
                mapper.mapDbModelToEntity(entity)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(dao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}