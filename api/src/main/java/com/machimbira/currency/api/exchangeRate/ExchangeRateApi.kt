package com.machimbira.currency.api.exchangeRate

import android.util.Log
import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.domain.mapper.ExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.network.resources.exchangeRate.IExchangeRateResources
import com.machimbira.currency.persistence.repository.exchangeRates.IExchangeRateRepository

class ExchangeRateApi(val exchangeRateResources: IExchangeRateResources, val rateMapper: ExchangeRateMapper, val rateRepository: IExchangeRateRepository): IExchangeRateApi{

    override fun getExchangeRates(callback: IResultCallback<ExchangeRate>) {
        this.exchangeRateResources.getExchangeRates(object : ResultCallback<ExchangeRateModel>(){
            override fun succeed(result: ExchangeRateModel) {
                if(result != null){
                    rateRepository.deleteAll()
                }
                val rates = rateMapper.mapToDomain(result)
                for(rate in rates.rate){
                    val persistedRate = rateMapper.mapToPersistedModel(result.base, result.timestamp, rate)
                    rateRepository.add(model = persistedRate)
                }
                callback.succeed(rates)
            }

            override fun fail(messages: List<String>) {
                Log.d("rates", "error")
            }
        })
    }

    override fun getPersistedExchangeRates(): List<Exchange> {
        val rates = rateRepository.getAll()
        val exchangeRateList = rateMapper.mapPersistedRateListToDomain(rates)
        return exchangeRateList
    }

    override fun getExchangeRateByCode(code: Long): Exchange {
        val selectedExchangeRate = rateRepository.getExchangeRateByCode(code = code)
        val exchangeRate = rateMapper.mapPersistenceExchangeRateToDomain(selectedExchangeRate)
        return exchangeRate
    }


}