package com.machimbira.currency.network.resources.currency

import com.machimbira.currency.network.mapper.CurrencyMapper
import retrofit2.Retrofit

class CurrencyResourceFactory{
    companion object {

        fun create(retrofit: Retrofit, currencyMapper: CurrencyMapper): ICurrencyResources{
            return CurrencyResources(retrofit = retrofit, currencyMapper = currencyMapper)
        }
    }
}