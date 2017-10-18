package com.machimbira.currency.features.startUpScreen

import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.exchangeRate.IExchangeRateApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate

class CurrencyPresenter(private val currencyApi: ICurrencyApi, private val ratesApi: IExchangeRateApi): ICurrencyContract.UserActions{
    private lateinit var view: ICurrencyContract.View

    init {
        this.getExchangeRates()
    }

    override fun getExchangeRates() {
        this.ratesApi.getExchangeRates(object : ResultCallback<ExchangeRate>(){})
    }

    override fun getMyCurrencies() {
        val myCurrencies = this.currencyApi.getPersistedCurrencies()
        if(!myCurrencies.isEmpty()){
            view.showMyCurrencies(myCurrencies)
        }
    }

    override fun takeView(view: ICurrencyContract.View) {
        this.view = view
    }

    override fun getCurrenciesList(){
        this.currencyApi.getCurrencyList(object : ResultCallback<List<Currency>>() {})
    }
}