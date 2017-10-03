package com.machimbira.currency.features.currencyDetailScreen

import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.trackedExchangeRates.ITrackedExchangeRatesApi

class CurrencyDetailPresenter(val view: CurrencyDetailActivity, val trackedRateApi: ITrackedExchangeRatesApi, val currencyApi: ICurrencyApi) {

    fun getRateByCode(code: String) {
        val rateOverTime = trackedRateApi.getExchangeRateByCode(code = code)
        view.loadRates(rates = rateOverTime)
    }

    fun deleteCurrency(code: String) {
        currencyApi.delete(code = code)
        view.backToHome()
    }
}