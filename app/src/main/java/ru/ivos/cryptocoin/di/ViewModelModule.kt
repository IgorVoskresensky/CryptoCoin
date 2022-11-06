package ru.ivos.cryptocoin.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ivos.cryptocoin.presentation.CoinViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindViewModel(viewModel: CoinViewModel): ViewModel
}