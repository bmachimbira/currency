package com.machimbira.currency.api.currency

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate

interface ICurrencyApi {
    fun getCurrencyList(callback: ResultCallback<List<Currency>>)
    fun getExchangeRates(callback: IResultCallback<ExchangeRate>)
    fun getPersistedCurrencies(): List<Currency>
}