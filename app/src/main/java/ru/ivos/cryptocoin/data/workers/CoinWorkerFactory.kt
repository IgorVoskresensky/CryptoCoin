package ru.ivos.cryptocoin.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.ivos.cryptocoin.di.ChildWorkerFactory
import javax.inject.Inject
import javax.inject.Provider

class CoinWorkerFactory @Inject constructor(
    private val workerProvides: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val child = when(workerClassName){
            RefreshDataWorker::class.qualifiedName -> {
                workerProvides[RefreshDataWorker::class.java]?.get()
            }
            else -> null
        }
        return child?.create(appContext, workerParameters)
    }
}