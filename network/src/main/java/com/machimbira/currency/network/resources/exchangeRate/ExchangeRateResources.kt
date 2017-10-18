package com.machimbira.currency.network.resources.exchangeRate

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.BuildConfig
import com.machimbira.currency.network.ExchangeRatesService
import com.machimbira.currency.network.callback.ExchangeRateCallback
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import retrofit2.Retrofit

class ExchangeRateResources(private val exchangeRateService: ExchangeRatesService, private val exchangeRateMapper: ExchangeRateMapper) : IExchangeRateResources {

    override fun getExchangeRates(callback: IResultCallback<ExchangeRateModel>) {
        val call = exchangeRateService.getRates(appId = BuildConfig.OPEN_EXCHANGE_RATE_KEY)
        call.enqueue(ExchangeRateCallback(callback, exchangeRateMapper))
    }
}