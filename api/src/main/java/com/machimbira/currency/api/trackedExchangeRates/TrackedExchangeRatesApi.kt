package com.machimbira.currency.api.trackedExchangeRates

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.domain.mapper.ExchangeRateMapper
import com.machimbira.currency.domain.mapper.TrackedExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.network.resources.exchangeRate.IExchangeRateResources
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import com.machimbira.currency.persistence.repository.trackedCurrencies.ITrackedRateRepository
import java.util.*

class TrackedExchangeRatesApi(val exchangeRateResources: IExchangeRateResources, val rateMapper: TrackedExchangeRateMapper, val rateRepository: ITrackedRateRepository, val exchangeRateRepository: ExchangeRateRepository, val exchangeRateMapper: ExchangeRateMapper): ITrackedExchangeRatesApi {

    override fun getExchangeRates(callback: IResultCallback<ExchangeRate>) {
        this.exchangeRateResources.getExchangeRates(object : ResultCallback<ExchangeRateModel>(){
            override fun succeed(result: ExchangeRateModel) {
                if(result != null){
                    exchangeRateRepository.deleteAll()
                }
                val rates = rateMapper.mapToDomain(result)
                for(rate in rates.rate){
                    val persistedRate = exchangeRateMapper.mapToPersistedModel(result.base, result.timestamp, rate)
                    exchangeRateRepository.add(model = persistedRate)
                }
                callback.succeed(rates)
            }

            override fun fail(messages: List<String>) {

            }
        })
    }

    override fun getPersistedExchangeRates(): List<Exchange> {
        val rates = rateRepository.getAll()
        val exchangeRateList = rateMapper.mapPersistedRateListToDomain(rates)
        return exchangeRateList
    }

    override fun getExchangeRateByCode(code: String): List<Exchange> {
        val selectedExchangeRate = rateRepository.getAllExchangeRateByCode(code = code)
        val exchangeRate = rateMapper.mapPersistedRateListToDomain(selectedExchangeRate)
        return exchangeRate
    }

    override fun saveRate(exchangeRate: Exchange) {
        val model = rateMapper.mapToPersistedModel(base = "USD", timestamp = Date().time, rate = exchangeRate)
        rateRepository.add(model)
    }
}