package com.machimbira.currency.features.currencyDetailScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.machimbira.currency.R
import com.machimbira.currency.api.trackedExchangeRates.TrackedExchangeRateApiFactory
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.domain.Exchange
import com.machimbira.currency.domain.mapper.TrackedExchangeRateMapper
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.persistence.repository.trackedCurrencies.TrackedRatesRepository
import kotlinx.android.synthetic.main.activity_currency_detail.*
import java.text.SimpleDateFormat
import java.util.*


class CurrencyDetailActivity : AppCompatActivity() {

    companion object {
        val CODE = "CURRENCY_CODE"
    }
//todo add list click, add currency code bundle to list click
    private lateinit var presenter: CurrencyDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_currency_detail)

        val code = intent.getStringExtra(CODE)

        presenter = CurrencyDetailPresenter(
                view = this,
                trackedRateApi = TrackedExchangeRateApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        exchangeRateRepository = TrackedRatesRepository(),
                        exchangeRateMapper = ExchangeRateMapper(),
                        trackedExchangeRateMapper = TrackedExchangeRateMapper())
        )

        presenter.getRateByCode(code = code)
        currency_name.text = String.format(resources.getString(R.string.trend), code)
    }

    fun loadRates(rates: List<Exchange>) {
        val entries = mutableListOf<Entry>()
        val timestamps = mutableListOf<String>()
        for ((index, rate) in rates.withIndex()){
            val entry = Entry(index.toFloat(), rate.rate.toFloat())
            val timestamp = getDateFromTimeStamp(rate.timestamp)
            entries.add(entry)
            timestamps.add(timestamp)
        }

        val dataSet = LineDataSet(entries, getString(R.string.exchange_rate) )
        val data = LineData(dataSet)
        data.setDrawValues(false)
        rate_chart.description.isEnabled = false
        rate_chart.legend.isEnabled = false
        rate_chart.data = data

    }

    private fun getDateFromTimeStamp(timestamp: Long): String {
        val date = Date(timestamp)
        val df2 = SimpleDateFormat("dd/MM/yy", Locale.UK)
        val dateText = df2.format(date)
        return dateText
    }


}
