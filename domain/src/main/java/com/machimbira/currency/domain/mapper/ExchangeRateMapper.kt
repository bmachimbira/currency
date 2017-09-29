package com.machimbira.currency.domain.mapper

import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.persistence.model.PersistenceExchangeRateModel

class ExchangeRateMapper {
    fun mapToDomain(model: ExchangeRateModel): ExchangeRate {
        val exchangeRates = model!!.rates.map { this.mapExchangeRateToDomain(it) }
        val exchangeRate = ExchangeRate(model!!.base, exchangeRates, model.timestamp)
        return exchangeRate
    }

    private fun mapExchangeRateToDomain(exchange: ExchangeRateModel.Exchange): Exchange {
        return Exchange(code = exchange.code, rate = exchange.rate)
    }

    fun mapPersistenceModelToDomain(result: PersistenceExchangeRateModel?): ExchangeRate {
        val rate = mapPersistenceExchangeRateToDomain(result!!)
        return ExchangeRate(code = result.currencyCode, rate = listOf(rate), timestamp = result.timestamp)
    }

    fun mapPersistennceModelListToDomain(result: List<PersistenceExchangeRateModel>): List<ExchangeRate> {
        val rates = result.map { this.mapPersistenceModelToDomain(it) }
        return rates
    }

    private fun mapPersistenceExchangeRateToDomain(rate: PersistenceExchangeRateModel): Exchange{
        return Exchange(code = rate.currencyCode, rate = rate.rate)
    }

    fun mapPersistedRateListToDomain(result: List<PersistenceExchangeRateModel>?): List<Exchange> {
        val exchangeRates = result!!.map { mapPersistenceExchangeRateToDomain(it) }
        return exchangeRates
    }
}
