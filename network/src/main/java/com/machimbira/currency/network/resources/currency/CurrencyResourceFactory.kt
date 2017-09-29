package com.machimbira.currency.network.resources.currency

import retrofit2.Retrofit

class CurrencyResourceFactory{
    companion object {
        fun create(retrofit: Retrofit): ICurrencyResources{
            return CurrencyResources(retrofit = retrofit)
        }
    }
}