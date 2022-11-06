package ru.ivos.cryptocoin.data.mapper

import ru.ivos.cryptocoin.data.network.model.CoinNamesListDto


class NamesMapper {

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { it.coinInfo?.name }?.joinToString(",") ?: ""
    }
}