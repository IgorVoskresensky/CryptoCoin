package ru.ivos.cryptocoin.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ivos.cryptocoin.data.workers.RefreshDataWorker

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}