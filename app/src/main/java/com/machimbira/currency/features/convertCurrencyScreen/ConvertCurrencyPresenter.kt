package com.machimbira.currency.features.convertCurrencyScreen

import com.machimbira.currency.api.exchangeRate.IExchangeRateApi
import com.machimbira.currency.common.ResultCallback
import com.machimbira.currency.domain.ExchangeRate

class ConvertCurrencyPresenter(private val exchangeRateApi: IExchangeRateApi) : IConvertCurrencyContract.UserActions {
    private lateinit var view: IConvertCurrencyContract.View
    private var exchangeRates = mutableListOf<Double>()
    private var currencyCodes = mutableListOf<String>()

    private var selectedIndex: Int = 0

    override fun takeView(view: IConvertCurrencyContract.View) {
        this.view = view
    }

    override fun getAllExchangeRates() {
        this.exchangeRateApi.getExchangeRates(object : ResultCallback<ExchangeRate>() {
            override fun succeed(result: ExchangeRate) {
                super.succeed(result)

                val rates = result.rate
                for (rate in rates) {
                    exchangeRates.add(rate.rate)
                    currencyCodes.add(rate.code)
                }
                view.populateCurrencyList(rates = currencyCodes)
                view.enableAmountInput()
            }

            override fun fail(messages: List<String>) {
                super.fail(messages)
            }
        })
    }

    override fun setSelectedCurrency(selected: Int) {
        this.selectedIndex = selected
    }

    override fun updateAmount(amount: CharSequence) {
        if (amount.isEmpty()) {
            return
        }

        val amountEntered = amount.toString()
        val amountToConvert = amountEntered.toDouble()
        if (amountToConvert == 0.0) {
            view.showInvalidInputMessage()
            return
        }
        val convertedAmount = this.convertCurrency(amountToConvert)
        if (currencyCodes.isEmpty()) {
            return
        }
        view.showConversionResult(currency = currencyCodes[selectedIndex], convertedAmount = convertedAmount)
    }

    private fun convertCurrency(amountToConvert: Double): Pair<Double, Double> {

        val convertedCurrency = amountToConvert * exchangeRates[selectedIndex]
        if (amountToConvert < 199) {
            val amountWithMarkup = addUsualMarkup(convertedCurrency)
            return Pair(convertedCurrency, amountWithMarkup)
        }
        val amountWithMarkup = addDiscountedMarkup(convertedCurrency)
        return Pair(convertedCurrency, amountWithMarkup)
    }

    private fun addDiscountedMarkup(convertedCurrency: Double): Double {
        return convertedCurrency * 1.07
    }

    private fun addUsualMarkup(convertedCurrency: Double): Double {
        return convertedCurrency * 1.04
    }
}