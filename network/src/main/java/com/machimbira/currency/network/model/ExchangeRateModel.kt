package com.machimbira.currency.network.model

data class ExchangeRateModel(var base: String, var rates: List<Exchange>, var timestamp: Long) {
    class Exchange (var code:String, var rate:Double)
}