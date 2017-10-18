package com.machimbira.currency.service

import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.trackedExchangeRates.ITrackedExchangeRatesApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate


class TrackedCurrencyRatesPresenter(val trackedExchangeRatesApi: ITrackedExchangeRatesApi, val currencyApi: ICurrencyApi) {
    private lateinit var mySavedCurrencies: List<Currency>
    private lateinit var currencyService: CurrencyRatesService

    init {
        getPersistedCurrencies()
    }

    fun takeService(currencyService: CurrencyRatesService){
        this.currencyService = currencyService
    }

    fun getAllExchangeRates() {
        this.trackedExchangeRatesApi.getExchangeRates(object : ResultCallback<ExchangeRate>() {
            override fun succeed(result: ExchangeRate) {
                super.succeed(result)

                val rates = result.rate

                for (currency in mySavedCurrencies) {
                    for (rate in rates) {
                        if (rate.code == currency.code) {
                            trackedExchangeRatesApi.saveRate(exchangeRate = rate)
                            if (rate.rate > currency.minimum) {
                                currencyService.sendNotification(code = currency.code)
                            }
                        }
                    }
                }
            }

            override fun fail(messages: List<String>) {
                super.fail(messages)
            }
        })
    }

    private fun getPersistedCurrencies() {
        mySavedCurrencies = currencyApi.getPersistedCurrencies()
    }
}