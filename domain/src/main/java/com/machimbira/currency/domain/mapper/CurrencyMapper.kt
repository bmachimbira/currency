package com.machimbira.currency.domain.mapper

import com.machimbira.currency.domain.Currency
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel

class CurrencyMapper {

    fun mapCurrenciesToDomain(model: Map<String, Any>): List<Currency> {
        var currencyList = mutableListOf<Currency>()
       model!!.forEach { currencyModel ->
            val currency = Currency(code = currencyModel.key, description = currencyModel.value as String)
            currencyList.add(currency)
        }
        return currencyList
    }

    fun mapPersistenceModelToDomain(result: PersistenceCurrencyModel?): Currency {
        return Currency(code = result!!.code!!, description = result!!.description!!)
    }

    fun mapPersistenceListToDomain(result: List<PersistenceCurrencyModel>): List<Currency> {
        val currencyList = result.map { this.mapPersistenceModelToDomain(it) }
        return currencyList
    }
}