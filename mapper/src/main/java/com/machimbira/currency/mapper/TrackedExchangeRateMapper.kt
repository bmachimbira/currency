package com.machimbira.currency.mapper

import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.persistence.model.TrackedRateModel
import java.util.*

class TrackedExchangeRateMapper {
    fun mapToDomain(model: ExchangeRateModel): ExchangeRate {
        var rateList = mutableListOf<Exchange>()
        var rates = model.rates
        rates.forEach { rate ->
            val exchange = Exchange(code = rate.key, rate = rate.value, timestamp = model.timestamp)
            rateList.add(exchange)
        }

        val exchangeRate = ExchangeRate(model!!.base, rateList, model.timestamp)
        return exchangeRate
    }

    fun mapPersistenceModelToDomain(result: TrackedRateModel?): ExchangeRate {
        val rate = mapPersistenceExchangeRateToDomain(result!!)
        return ExchangeRate(code = result.code!!, rate = listOf(rate), timestamp = result.timestamp)
    }

    fun mapPersistennceModelListToDomain(result: List<TrackedRateModel>): List<ExchangeRate> {
        val rates = result.map { this.mapPersistenceModelToDomain(it) }
        return rates
    }

    fun mapPersistenceExchangeRateToDomain(persistenceRateModel: TrackedRateModel): Exchange {
        return Exchange(code = persistenceRateModel.code!!, rate = persistenceRateModel.rate, timestamp = persistenceRateModel.timestamp)
    }

    fun mapPersistedRateListToDomain(result: List<TrackedRateModel>?): List<Exchange> {
        val exchangeRates = result!!.map { mapPersistenceExchangeRateToDomain(it) }
        return exchangeRates
    }

    fun mapToPersistedModel(base: String, timestamp: Long, rate: Exchange): TrackedRateModel {
        var persistenceRateModel = TrackedRateModel()
        persistenceRateModel.base = base
        persistenceRateModel.code = rate.code
        persistenceRateModel.rate = rate.rate
        persistenceRateModel.timestamp = timestamp
        return persistenceRateModel
    }


}