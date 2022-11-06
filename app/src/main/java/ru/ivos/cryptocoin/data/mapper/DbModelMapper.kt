package ru.ivos.cryptocoin.data.mapper

import com.example.cryptoapp.utils.convertTimestampToTime
import ru.ivos.cryptocoin.data.database.CoinInfoDbModel
import ru.ivos.cryptocoin.domain.CoinInfo


class DbModelMapper {

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )
}