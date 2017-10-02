package com.machimbira.currency.api.trackedExchangeRates

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate

interface ITrackedExchangeRatesApi{
    fun getExchangeRates(callback: IResultCallback<ExchangeRate>)
    fun getPersistedExchangeRates(): List<Exchange>
    fun getExchangeRateByCode(code: String): List<Exchange>
    fun saveRate(exchangeRate: Exchange)
}