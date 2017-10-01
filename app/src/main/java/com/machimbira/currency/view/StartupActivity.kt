package com.machimbira.currency.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.machimbira.currency.CurrencyApplication
import com.machimbira.currency.ICurrencyContract
import com.machimbira.currency.R
import com.machimbira.currency.api.currency.CurrencyApiFactory
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.persistence.repository.CurrencyRepository
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
import com.machimbira.currency.presenter.currency.CurrencyPresenter

import kotlinx.android.synthetic.main.activity_startup.*

class StartupActivity : AppCompatActivity(), ICurrencyContract.View {

    private lateinit var presenter: ICurrencyContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
        setSupportActionBar(toolbar)

        presenter = CurrencyPresenter(
                currencyApi = CurrencyApiFactory.create(
                        context = this,
                        retrofit = CurrencyApplication.getClient(),
                        currencyRepository = CurrencyRepository(),
                        exchangeRateRepository = ExchangeRateRepository()),
                view = this)

        presenter.getMyCurrencies()

        fab.setOnClickListener { _ ->
            presenter.getCurrenciesList()
        }
    }

    override fun showMyCurrencies(myCurrencies: List<Currency>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
