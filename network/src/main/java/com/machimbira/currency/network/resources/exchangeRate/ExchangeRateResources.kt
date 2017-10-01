package com.machimbira.currency.network.resources.exchangeRate

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.BuildConfig
import com.machimbira.currency.network.ExchangeRatesService
import com.machimbira.currency.network.callback.ExchangeRateCallback
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import retrofit2.Retrofit

class ExchangeRateResources(private val retrofit: Retrofit, private val exchangeRateMapper: ExchangeRateMapper) : IExchangeRateResources {
    private val exchangeRateService: ExchangeRatesService

    init {
        this.exchangeRateService = retrofit.create(ExchangeRatesService::class.java)
    }

    override fun getExchangeRate(callback: IResultCallback<ExchangeRateModel>) {
        val call = exchangeRateService.getRates(appId = BuildConfig.OPEN_EXCHANGE_RATE_KEY)
        call.enqueue(ExchangeRateCallback(callback, exchangeRateMapper))
    }
}