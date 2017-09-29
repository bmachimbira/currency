package com.machimbira.currency.network.resources.currency

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.model.CurrencyModel

interface ICurrencyResources {
    fun getCurrencyList(callback: IResultCallback<List<CurrencyModel>>)
}
