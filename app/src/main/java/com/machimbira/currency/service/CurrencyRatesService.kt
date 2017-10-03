package com.machimbira.currency.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.machimbira.currency.R
import com.machimbira.currency.api.currency.CurrencyApiFactory
import com.machimbira.currency.api.trackedExchangeRates.TrackedExchangeRateApiFactory
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.domain.mapper.TrackedExchangeRateMapper
import com.machimbira.currency.features.currencyDetailScreen.CurrencyDetailActivity
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.persistence.repository.currency.CurrencyRepository
import com.machimbira.currency.persistence.repository.trackedCurrencies.TrackedRatesRepository


class CurrencyRatesService : Service() {
    private lateinit var trackedCurrencyRatesPresenter: TrackedCurrencyRatesPresenter

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
         val trackedRatesApi = TrackedExchangeRateApiFactory.create(
                retrofit = CurrencyApplication.getClient(),
                exchangeRateRepository = TrackedRatesRepository(),
                exchangeRateMapper = ExchangeRateMapper(),
                trackedExchangeRateMapper = TrackedExchangeRateMapper()
                )

        val currencyApi = CurrencyApiFactory.create(
                retrofit = CurrencyApplication.getClient(),
                currencyRepository = CurrencyRepository())

        trackedCurrencyRatesPresenter = TrackedCurrencyRatesPresenter(currencyService = this,trackedExchangeRatesApi = trackedRatesApi, currencyApi = currencyApi)

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        trackedCurrencyRatesPresenter.getAllExchangeRates()
        return START_STICKY
    }

    fun sendNotification(code: String) {
            val NOTIFICATION_ID = 1
            val mBuilder = NotificationCompat.Builder(this)
            val intent = Intent(this, CurrencyDetailActivity::class.java)
            intent.putExtra(CurrencyDetailActivity.CODE, code)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            mBuilder.setContentIntent(pendingIntent)

            mBuilder.setSmallIcon(R.drawable.ic_attach_money_black_24dp)
            mBuilder.setContentTitle(String.format(this.getString(R.string.notification_content), code))
            mBuilder.setContentText(getString(R.string.app_name))

            val notification = mBuilder.build()

            startForeground(NOTIFICATION_ID, notification)

    }

}