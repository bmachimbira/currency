package com.machimbira.currency.network.resources.exchangeRate

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.model.ExchangeRateModel

interface IExchangeRateResources{
    fun getExchangeRates(callback: IResultCallback<ExchangeRateModel>)

}