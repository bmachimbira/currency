package com.machimbira.currency.features.addCurrencyScreen

import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.exchangeRate.IExchangeRateApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.domain.Exchange

class AddCurrencyPresenter(val view: IAddCurrencyContract.View, val currencyApi: ICurrencyApi, private val exchangeRateApi: IExchangeRateApi): IAddCurrencyContract.UserActions{
    private var currencyCodes = mutableListOf<String>()
    private var rates = mutableListOf<Double>()
    private var selectedIndex = 0


    init {
        val exchangeRates = exchangeRateApi.getPersistedExchangeRates()
        exchangeRates
                .map { it.code }
                .forEach { currencyCodes.add(it) }

        exchangeRates
                .map { it.rate }
                .forEach { rates.add(it) }
        view.populateAutoCompleteListWithRates(exchangeRates = currencyCodes)
    }

    override fun getExchangeRateByCode(code: Long): Exchange{
        return exchangeRateApi.getExchangeRateByCode(code = code)
    }


    override fun getRateForSelectedCurrency(selected: Int) {
        this.selectedIndex = selected
        val selectedRate = rates[selected]
        view.showCurrentValue(currentRate = selectedRate)
    }

    override fun saveCurrency(minimumValue: String) {

        val selectedCode = currencyCodes[selectedIndex]
        this.currencyApi.getCurrencyList(object : ResultCallback<List<Currency>>() {
            override fun succeed(result: List<com.machimbira.currency.domain.Currency>) {
                super.succeed(result)
                val description = result
                        .firstOrNull { it.code == selectedCode }
                        ?.description
                        ?: ""
                if (description.isEmpty()){
                    return
                }
                currencyApi.saveSelectedCurrency(code = currencyCodes[selectedIndex], rate = rates[selectedIndex], minimumValue = minimumValue, description = description)
                view.backToHome()
            }
        })
    }
}