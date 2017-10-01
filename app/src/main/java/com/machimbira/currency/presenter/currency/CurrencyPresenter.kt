package com.machimbira.currency.presenter.currency

import com.machimbira.currency.ICurrencyContract
import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.ExchangeRate

class CurrencyPresenter(val view: ICurrencyContract.View, val currencyApi: ICurrencyApi): ICurrencyContract.UserActions{

    private lateinit var rates: List<ExchangeRate>
    init {
        this.getExchangeRates()
    }

    override fun getExchangeRates() {
        this.currencyApi.getExchangeRates(object : ResultCallback<ExchangeRate>(){
            override fun succeed(result: ExchangeRate) {
                super.succeed(result)
            }

            override fun fail(messages: List<String>) {
                super.fail(messages)
            }
        })
    }

    override fun getMyCurrencies(){
        val myCurrencies = this.currencyApi.getPersistedCurrencies()
        if(!myCurrencies.isEmpty()){
            view.showMyCurrencies(myCurrencies)
        }
    }

    override fun getCurrenciesList(){
        this.currencyApi.getCurrencyList(object : ResultCallback<List<Currency>>() {
            override fun succeed(result: List<com.machimbira.currency.domain.Currency>) {
                super.succeed(result)
            }

            override fun fail(messages: List<String>) {
                super.fail(messages)
            }
        })
    }
}