package com.machimbira.currency.api.currency

import com.machimbira.currency.mapper.CurrencyMapper
import com.machimbira.currency.network.resources.currency.CurrencyResourceFactory
import com.machimbira.currency.persistence.repository.currency.CurrencyRepository
import retrofit2.Retrofit

class CurrencyApiFactory {
    companion object {
        fun create(retrofit: Retrofit, currencyRepository: CurrencyRepository): ICurrencyApi {
            val currencyResources = CurrencyResourceFactory.create(retrofit = retrofit, currencyMapper = com.machimbira.currency.network.mapper.CurrencyMapper())
            return CurrencyApi(
                    currencyResources = currencyResources,
                    currencyRepository = currencyRepository,
                    currencyMapper = CurrencyMapper())
        }
    }

}