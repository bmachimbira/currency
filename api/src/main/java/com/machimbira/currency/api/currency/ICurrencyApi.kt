package com.machimbira.currency.api.currency

import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency

interface ICurrencyApi {
    fun getCurrencyList(callback: ResultCallback<List<Currency>>)
    fun getPersistedCurrencies(): List<Currency>
    fun saveSelectedCurrency(code: String, rate: Double, minimumValue: String, description: String)
    fun delete(code: String)
}