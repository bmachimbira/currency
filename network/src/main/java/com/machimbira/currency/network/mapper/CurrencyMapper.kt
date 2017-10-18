package com.machimbira.currency.network.mapper

import com.machimbira.currency.common.GsonConverter
import okhttp3.ResponseBody


class CurrencyMapper {
    fun mapResponseToModel(body: ResponseBody?): Map<String, Any> {
        val map = GsonConverter.mapStringToMap(body!!.string())
        return map
    }
}