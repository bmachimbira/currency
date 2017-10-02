package com.machimbira.currency.features.currencyDetailScreen

import com.machimbira.currency.api.trackedExchangeRates.ITrackedExchangeRatesApi

class CurrencyDetailPresenter(val view: CurrencyDetailActivity, val trackedRateApi: ITrackedExchangeRatesApi) {

    fun getRateByCode(code: String) {
        val rateOverTime = trackedRateApi.getExchangeRateByCode(code = code)
        view.loadRates(rates = rateOverTime)
    }
}