package com.machimbira.currency.network.resources.exchangeRate

import com.machimbira.currency.network.mapper.ExchangeRateMapper
import retrofit2.Retrofit

class ExchangeRateResourceFactory{
    companion object {
        fun create(retrofit: Retrofit, exchangeRateMapper: ExchangeRateMapper): ExchangeRateResources{
            return ExchangeRateResources(retrofit = retrofit, exchangeRateMapper = exchangeRateMapper)
        }
    }
}