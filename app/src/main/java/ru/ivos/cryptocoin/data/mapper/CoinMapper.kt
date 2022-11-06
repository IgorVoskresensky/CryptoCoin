package ru.ivos.cryptocoin.data.mapper

import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.Gson
import ru.ivos.cryptocoin.data.database.CoinInfoDbModel
import ru.ivos.cryptocoin.data.network.ApiFactory.BASE_IMAGE_URL
import ru.ivos.cryptocoin.data.network.model.CoinInfoDto
import ru.ivos.cryptocoin.data.network.model.CoinInfoJsonContainerDto
import ru.ivos.cryptocoin.data.network.model.CoinNamesListDto
import ru.ivos.cryptocoin.domain.CoinInfo
import javax.inject.Inject


class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

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

    fun mapJsonToCoinInfo(json: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = json.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { it.coinInfo?.name }?.joinToString(",") ?: ""
    }
}