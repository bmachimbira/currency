package com.machimbira.currency.domain.mapper

import com.machimbira.currency.domain.Currency
import com.machimbira.currency.network.model.CurrencyModel
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel

class CurrencyMapper {
    private fun mapCurrencyToDomain(model: CurrencyModel): Currency{
        return Currency(model.code, model.description)
    }

    fun mapCurrenciesToDomain(model: List<CurrencyModel>): List<Currency> {
        var currencyList = mutableListOf<Currency>()
       model.forEach { currencyModel ->
            val currency = this.mapCurrencyToDomain(currencyModel)
            currencyList.add(currency)
        }
        return currencyList
    }

    fun mapPersistenceModelToDomain(result: PersistenceCurrencyModel?): Currency {
        return Currency(code = result!!.code, description = result!!.description)
    }

    fun mapPersistenceListToDomain(result: List<PersistenceCurrencyModel>): List<Currency> {
        val currencyList = result.map { this.mapPersistenceModelToDomain(it) }
        return currencyList
    }
}