package ru.ivos.cryptocoin.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.ivos.cryptocoin.presentation.CoinApp
import ru.ivos.cryptocoin.presentation.CoinDetailFragment
import ru.ivos.cryptocoin.presentation.CoinPriceListActivity

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create( @BindsInstance application: Application): ApplicationComponent
    }
}