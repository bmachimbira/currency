package com.machimbira.currency.api.currency

import android.content.Context
import com.machimbira.currency.domain.mapper.CurrencyMapper
import com.machimbira.currency.domain.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.currency.CurrencyResourceFactory
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.CurrencyRepository
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
import retrofit2.Retrofit

class CurrencyApiFactory {
    companion object {
        fun create(context: Context, retrofit: Retrofit, currencyRepository: CurrencyRepository, exchangeRateRepository: ExchangeRateRepository): ICurrencyApi {
            val currencyResources = CurrencyResourceFactory.create(retrofit = retrofit, currencyMapper = com.machimbira.currency.network.mapper.CurrencyMapper())
            val exchangeResources = ExchangeRateResourceFactory.create(retrofit = retrofit, exchangeRateMapper = com.machimbira.currency.network.mapper.ExchangeRateMapper())
            return CurrencyApi(
                    currencyResources = currencyResources,
                    currencyRepository = currencyRepository,
                    currencyMapper = CurrencyMapper(),
                    exchangeRateResources = exchangeResources,
                    rateMapper = ExchangeRateMapper(),
                    rateRepository = exchangeRateRepository
                    )
        }
    }

}