package com.machimbira.currency.api.trackedExchangeRates

import com.machimbira.currency.mapper.TrackedExchangeRateMapper
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import com.machimbira.currency.persistence.repository.trackedCurrencies.TrackedRatesRepository
import retrofit2.Retrofit

class TrackedExchangeRateApiFactory {
    companion object {
        fun create(retrofit: Retrofit, exchangeRateRepository: TrackedRatesRepository, trackedExchangeRateMapper: TrackedExchangeRateMapper, exchangeRateMapper: ExchangeRateMapper): ITrackedExchangeRatesApi {
            val exchangeRateResources = ExchangeRateResourceFactory.create(retrofit = retrofit, exchangeRateMapper = exchangeRateMapper)
            return TrackedExchangeRatesApi(exchangeRateResources = exchangeRateResources, rateMapper = trackedExchangeRateMapper, rateRepository = exchangeRateRepository, exchangeRateMapper = com.machimbira.currency.mapper.ExchangeRateMapper(), exchangeRateRepository = ExchangeRateRepository())
        }
    }
}