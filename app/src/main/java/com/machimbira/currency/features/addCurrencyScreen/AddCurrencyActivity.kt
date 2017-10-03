package com.machimbira.currency.features.addCurrencyScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.machimbira.currency.R
import com.machimbira.currency.api.currency.CurrencyApiFactory
import com.machimbira.currency.api.exchangeRate.ExchangeRateApiFactory
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.persistence.repository.currency.CurrencyRepository
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import kotlinx.android.synthetic.main.activity_add_currency.*
import kotlinx.android.synthetic.main.content_add_currency.*

class AddCurrencyActivity : AppCompatActivity(), IAddCurrencyContract.View {

    private lateinit var presenter: IAddCurrencyContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_currency)
        setSupportActionBar(toolbar)

        presenter = AddCurrencyPresenter(
                view = this,
                currencyApi = CurrencyApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        currencyRepository = CurrencyRepository()),
                exchangeRateApi = ExchangeRateApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        exchangeRateRepository = ExchangeRateRepository(),
                        exchangeRateMapper = ExchangeRateMapper()))

        initialiseCurrencyListClickListener()


        fab.setOnClickListener { _ ->
            presenter.saveCurrency(minimumValue = currency_minimum_value.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        fab.isEnabled = true
    }

    private fun initialiseCurrencyListClickListener() {
        currencies_list.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                presenter.getRateForSelectedCurrency(selected = pos)
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }

        }
    }

    override fun populateAutoCompleteListWithRates(exchangeRates: List<String>) {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exchangeRates)
        currencies_list.setAdapter(adapter)
    }

    override fun showCurrentValue(currentRate: Double) {
        current_value.text = String.format(resources.getString(R.string.current_rate_string, currentRate))
    }

    override fun backToHome() {
        finish()
    }
}
