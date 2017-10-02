package com.machimbira.currency.api.exchangeRate

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.ExchangeRate

interface IExchangeRateApi{
    fun getExchangeRates(callback: IResultCallback<ExchangeRate>)
    fun getPersistedExchangeRates(): List<Exchange>
    fun getExchangeRateByCode(code: Long): Exchange
}