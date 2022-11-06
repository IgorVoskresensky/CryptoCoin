package ru.ivos.cryptocoin.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,

    @SerializedName("PRICE")
    @Expose
    val price: String?,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: String,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: String?,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: String?,

    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String
)