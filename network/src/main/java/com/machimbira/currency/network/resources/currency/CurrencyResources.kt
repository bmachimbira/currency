package com.machimbira.currency.network.resources.currency

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.CurrencyService
import com.machimbira.currency.network.callback.CurrencyCallback
import com.machimbira.currency.network.model.CurrencyModel
import retrofit2.Retrofit

class CurrencyResources(private val retrofit: Retrofit) : ICurrencyResources {
    private val currencyService: CurrencyService

    init {
        this.currencyService = retrofit.create(CurrencyService::class.java)
    }

    override fun getCurrencyList(callback: IResultCallback<List<CurrencyModel>>) {
        val call = currencyService.getCurrencies()
        call.enqueue(CurrencyCallback(callback))
    }
}
