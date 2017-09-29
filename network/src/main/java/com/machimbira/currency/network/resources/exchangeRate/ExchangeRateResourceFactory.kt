package com.machimbira.currency.network.resources.exchangeRate

import retrofit2.Retrofit

class ExchangeRateResourceFactory{
    companion object {
        fun create(retrofit: Retrofit): IExchangeRateResources{
            return ExchangeRateResources(retrofit = retrofit)
        }
    }
}