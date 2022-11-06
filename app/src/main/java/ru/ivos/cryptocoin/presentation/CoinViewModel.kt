package ru.ivos.cryptocoin.presentation

import androidx.lifecycle.ViewModel
import ru.ivos.cryptocoin.domain.GetCoinInfoListUseCase
import ru.ivos.cryptocoin.domain.GetCoinInfoUseCase
import ru.ivos.cryptocoin.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}