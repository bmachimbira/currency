package com.machimbira.currency.api.currency

import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.mapper.CurrencyPersistedMapper
import com.machimbira.currency.network.resources.currency.ICurrencyResources
import com.machimbira.currency.persistence.repository.currency.ICurrencyRepository

class CurrencyApi(val currencyResources: ICurrencyResources,
                  val currencyRepository:ICurrencyRepository,
                  val currencyMapper: CurrencyPersistedMapper) : ICurrencyApi{

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

    override fun saveSelectedCurrency(code: String, rate: Double, minimumValue: String, description: String) {
        val model = currencyMapper.mapToModel(code = code, rate = rate, description = description, minimumValue = minimumValue)
        currencyRepository.add(model = model)
    }

    override fun delete(code: String) {
        val model = currencyRepository.getCurrencyByCode(code = code)
        currencyRepository.delete(model = model)
    }
}