package ru.ivos.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import ru.ivos.cryptocoin.data.workers.CoinWorkerFactory
import ru.ivos.cryptocoin.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}