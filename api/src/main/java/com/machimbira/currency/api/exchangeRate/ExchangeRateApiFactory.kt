package com.machimbira.currency.api.exchangeRate

import android.content.Context
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
import retrofit2.Retrofit

class ExchangeRateApiFactory {
    companion object {
        fun create(context: Context, retrofit: Retrofit, exchangeRateRepository: ExchangeRateRepository, exchangeRateMapper: ExchangeRateMapper): IExchangeRateApi{
            val exchangeRateResources = ExchangeRateResourceFactory.create(retrofit = retrofit, exchangeRateMapper = exchangeRateMapper)
            return ExchangeRateApi(exchangeRateResources = exchangeRateResources, exchangePersistenceRateModelRepository = exchangeRateRepository)
        }
    }
}