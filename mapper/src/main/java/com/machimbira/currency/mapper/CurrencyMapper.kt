package com.machimbira.currency.mapper

import com.machimbira.currency.domain.Currency
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel

class CurrencyMapper {

    fun mapCurrenciesToDomain(model: Map<String, Any>): List<Currency> {
        var currencyList = mutableListOf<Currency>()
       model!!.forEach { currencyModel ->
            val currency = Currency(code = currencyModel.key, description = currencyModel.value as String, rate = 0.0, minimum = 0.0)
            currencyList.add(currency)
        }
        return currencyList
    }

    fun mapPersistenceModelToDomain(result: PersistenceCurrencyModel?): Currency {
        return Currency(code = result!!.code!!, description = result.description!!, rate = result.currentValue, minimum = result.minimumValue)
    }

    fun mapPersistenceListToDomain(result: List<PersistenceCurrencyModel>): List<Currency> {
        val currencyList = result.map { this.mapPersistenceModelToDomain(it) }
        return currencyList
    }

    fun mapToModel(code: String, rate: Double, description: String, minimumValue: String): PersistenceCurrencyModel {
        val model = PersistenceCurrencyModel()
        model.code = code
        model.currentValue = rate
        model.description = description
        model.minimumValue = minimumValue.toDouble()
        return model
    }
}