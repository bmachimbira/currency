package com.machimbira.currency.network.resources.currency

import com.machimbira.currency.common.IResultCallback

interface ICurrencyResources {
    fun getCurrencyList(callback: IResultCallback<Map<String, Any>>)
}
