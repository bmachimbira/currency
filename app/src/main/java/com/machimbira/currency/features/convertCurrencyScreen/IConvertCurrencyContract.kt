package com.machimbira.currency.features.convertCurrencyScreen

interface IConvertCurrencyContract{
    interface View {
        fun populateCurrencyList(rates: MutableList<String>)
        fun showConversionResult(currency: String, convertedAmount: Pair<Double, Double>)
        fun showInvalidInputMessage()
        fun enableAmountInput()
    }

    interface UserActions {
        fun getAllExchangeRates()
        fun setSelectedCurrency(selected: Int)
        fun updateAmount(amount: CharSequence)
        fun takeView(view: View)
    }

}