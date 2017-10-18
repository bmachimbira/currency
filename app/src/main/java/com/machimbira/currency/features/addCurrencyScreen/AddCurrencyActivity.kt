package com.machimbira.currency.features.addCurrencyScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.machimbira.currency.R
import com.machimbira.currency.configuration.CurrencyApplication
import kotlinx.android.synthetic.main.activity_add_currency.*
import kotlinx.android.synthetic.main.content_add_currency.*
import javax.inject.Inject


class AddCurrencyActivity : AppCompatActivity(), IAddCurrencyContract.View {

    @Inject
    private lateinit var presenter: IAddCurrencyContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_currency)

        CurrencyApplication.iocContainer.beginScope(this)
        CurrencyApplication.iocContainer.inject(this)

        initialiseCurrencyListClickListener()

        presenter.takeView(view = this)
        presenter.initialise()

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
        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, exchangeRates)
        currencies_list.adapter = adapter!!
    }

    override fun showCurrentValue(currentRate: Double) {
        current_value.text = String.format(resources.getString(R.string.current_rate_string, currentRate))
    }

    override fun backToHome() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        CurrencyApplication.iocContainer.endScope()
    }
}
