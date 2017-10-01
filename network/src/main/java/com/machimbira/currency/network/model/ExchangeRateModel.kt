package com.machimbira.currency.network.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateModel(
        @SerializedName("base") var base: String,
        @SerializedName("rates")var rates: Map<String, Double>,
        @SerializedName("timestamp")var timestamp: Long) {
}