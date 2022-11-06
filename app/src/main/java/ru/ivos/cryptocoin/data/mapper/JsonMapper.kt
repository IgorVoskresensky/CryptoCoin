package ru.ivos.cryptocoin.data.mapper

import com.google.gson.Gson
import ru.ivos.cryptocoin.data.network.model.CoinInfoDto
import ru.ivos.cryptocoin.data.network.model.CoinInfoJsonContainerDto

class JsonMapper {

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
}