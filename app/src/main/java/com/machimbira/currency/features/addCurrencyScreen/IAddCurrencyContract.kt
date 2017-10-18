package com.machimbira.currency.features.addCurrencyScreen

import com.machimbira.currency.domain.Exchange

interface IAddCurrencyContract{
    interface View {
        fun populateAutoCompleteListWithRates(exchangeRates: List<String>)
        fun showCurrentValue(currentRate: Double)
        fun backToHome()
    }

    interface UserActions {
        fun getExchangeRateByCode(code: Long): Exchange
        fun getRateForSelectedCurrency(selected: Int)
        fun saveCurrency(minimumValue: String)
        fun takeView(view: View)
        fun initialise()
    }
}