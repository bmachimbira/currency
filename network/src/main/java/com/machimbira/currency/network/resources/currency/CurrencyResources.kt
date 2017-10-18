package com.machimbira.currency.network.resources.currency

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.CurrencyService
import com.machimbira.currency.network.callback.CurrencyCallback
import com.machimbira.currency.network.mapper.CurrencyMapper
import retrofit2.Retrofit

class CurrencyResources(private val currencyService: CurrencyService, private val currencyMapper: CurrencyMapper) : ICurrencyResources {

    override fun getCurrencyList(callback: IResultCallback<Map<String, Any>>) {
        val call = currencyService.getCurrencies()
        call.enqueue(CurrencyCallback(callback, currencyMapper))
    }
}
