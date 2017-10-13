package com.machimbira.currency.api.exchangeRate

import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import retrofit2.Retrofit

class ExchangeRateApiFactory {
    companion object {
        fun create(retrofit: Retrofit, exchangeRateRepository: ExchangeRateRepository, exchangeRateMapper: ExchangeRateMapper): ExchangeRateApi{
            val exchangeRateResources = ExchangeRateResourceFactory.create(retrofit = retrofit, exchangeRateMapper = exchangeRateMapper)
            return ExchangeRateApi(exchangeRateResources = exchangeRateResources, rateMapper = com.machimbira.currency.mapper.ExchangeRateMapper(), rateRepository = exchangeRateRepository)
        }
    }
}