package com.machimbira.currency.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.machimbira.currency.R
import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.trackedExchangeRates.ITrackedExchangeRatesApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate
import com.machimbira.currency.features.currencyDetailScreen.CurrencyDetailActivity


class TrackedCurrencyRatesPresenter(val currencyService: CurrencyRatesService, val trackedExchangeRatesApi: ITrackedExchangeRatesApi, val currencyApi: ICurrencyApi) {
    private lateinit var mySavedCurrencies: List<Currency>

    init {
        getPersistedCurrencies()
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