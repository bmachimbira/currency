package com.machimbira.currency.network.mapper

import com.machimbira.currency.common.GsonConverter
import com.machimbira.currency.network.model.ExchangeRateModel
import okhttp3.ResponseBody

class ExchangeRateMapper {
    fun mapResponseToModel(body: ResponseBody?): ExchangeRateModel {
        val response = body!!.string()
        val exchangeRates = GsonConverter.deserialize(response, ExchangeRateModel::class.java)
        return exchangeRates
    }
}