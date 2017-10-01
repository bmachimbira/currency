package com.machimbira.currency.api.currency

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.domain.mapper.CurrencyMapper
import com.machimbira.currency.domain.mapper.ExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import com.machimbira.currency.network.resources.currency.ICurrencyResources
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResources
import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import com.machimbira.currency.persistence.model.PersistenceRateModel

class CurrencyApi(val currencyResources: ICurrencyResources,
                  val currencyRepository:IRepository<PersistenceCurrencyModel>,
                  val currencyMapper: CurrencyMapper,
                  val exchangeRateResources: ExchangeRateResources,
                  val rateRepository:IRepository<PersistenceRateModel>,
                  val rateMapper: ExchangeRateMapper): ICurrencyApi {

    override fun getExchangeRates(callback: IResultCallback<ExchangeRate>) {
        this.exchangeRateResources.getExchangeRate(object : ResultCallback<ExchangeRateModel>(){
            override fun succeed(result: ExchangeRateModel) {
                val currencies = rateMapper.mapToDomain(result!!)
                callback.succeed(currencies)
            }

            override fun fail(messages: List<String>) {

            }
        })
    }

    override fun getPersistedCurrencies(): List<Currency> {
        val persistedCurrencies = currencyRepository.getAll()
        val currencies = currencyMapper.mapPersistenceListToDomain(result = persistedCurrencies)
        return currencies
    }

    override fun getCurrencyList(callback: ResultCallback<List<Currency>>) {
        this.currencyResources.getCurrencyList(object : ResultCallback<Map<String, Any>>() {
            override fun succeed(result: Map<String, Any>) {
                val currencies = currencyMapper.mapCurrenciesToDomain(result!!)
                callback.succeed(currencies)
            }

            override fun fail(messages: List<String>) {

            }
        })
    }
}