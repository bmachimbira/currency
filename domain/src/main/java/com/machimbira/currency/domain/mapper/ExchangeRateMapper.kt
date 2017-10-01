package com.machimbira.currency.domain.mapper

import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.persistence.model.PersistenceRateModel

class ExchangeRateMapper {
    fun mapToDomain(model: ExchangeRateModel): ExchangeRate {
        var rateList = mutableListOf<Exchange>()
        var rates = model.rates
        rates.forEach { rate ->
            val exchange = Exchange(code = rate.key, rate = rate.value)
            rateList.add(exchange)
        }

        val exchangeRate = ExchangeRate(model!!.base, rateList, model.timestamp)
        return exchangeRate
    }

    fun mapPersistenceModelToDomain(result: PersistenceRateModel?): ExchangeRate {
        val rate = mapPersistenceExchangeRateToDomain(result!!)
        return ExchangeRate(code = result.code!!, rate = listOf(rate), timestamp = result.timestamp)
    }

    fun mapPersistennceModelListToDomain(result: List<PersistenceRateModel>): List<ExchangeRate> {
        val rates = result.map { this.mapPersistenceModelToDomain(it) }
        return rates
    }

    private fun mapPersistenceExchangeRateToDomain(persistenceRateModel: PersistenceRateModel): Exchange{
        return Exchange(code = persistenceRateModel.code!!, rate = persistenceRateModel.rate)
    }

    fun mapPersistedRateListToDomain(result: List<PersistenceRateModel>?): List<Exchange> {
        val exchangeRates = result!!.map { mapPersistenceExchangeRateToDomain(it) }
        return exchangeRates
    }
}
