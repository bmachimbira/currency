package com.machimbira.currency.api.currency

import com.machimbira.currency.domain.mapper.CurrencyMapper
import com.machimbira.currency.domain.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.currency.CurrencyResourceFactory
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.CurrencyRepository
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
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