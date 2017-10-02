package com.machimbira.currency.features.convertCurrencyScreen

interface IConvertCurrencyContract{
    interface View {
        fun populateCurrencyList(rates: MutableList<String>)
        fun showConversionResult(currency: String, convertedAmount: Pair<Double, Double>)
        fun showInvalidInputMessage()
    }

    interface UserActions {
        fun getAllExchangeRates()
        fun setSelectedCurrency(selected: Int)
        fun updateAmount(amount: CharSequence)
    }

}