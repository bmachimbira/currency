package com.machimbira.currency.features.convertCurrencyScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.machimbira.currency.R
import com.machimbira.currency.api.exchangeRate.ExchangeRateApiFactory
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
import kotlinx.android.synthetic.main.activity_convert_currency.*
import kotlinx.android.synthetic.main.activity_convert_currency.view.*


class ConvertCurrencyFragment : Fragment(), IConvertCurrencyContract.View {

    private lateinit var presenter: IConvertCurrencyContract.UserActions
    private lateinit var currencySpinner: Spinner
    private lateinit var amountToConvert: EditText

    companion object {
        fun newInstance(): ConvertCurrencyFragment {
            return ConvertCurrencyFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ConvertCurrencyPresenter(
                view = this,
                exchangeRateApi = ExchangeRateApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        exchangeRateRepository = ExchangeRateRepository(),
                        exchangeRateMapper = ExchangeRateMapper()))
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.activity_convert_currency, container, false)


        currencySpinner = view.currency_spinner
        amountToConvert = view.amount_to_convert
        presenter.getAllExchangeRates()

        setSpinnerListener()

        setTextChangeListener()
        return view
    }

    private fun setTextChangeListener() {
        amountToConvert.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                presenter.updateAmount(amount = s)
            }
        });
    }

    private fun setSpinnerListener() {
        currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                presenter.setSelectedCurrency(selected = pos)
                presenter.updateAmount(amount_to_convert.text)
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }

        }
    }

    override fun populateCurrencyList(rates: MutableList<String>) {
        if(activity != null) {
            val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, rates)
            currencySpinner.adapter = adapter
        }
    }

    override fun showConversionResult(currency: String, convertedAmount: Pair<Double, Double>) {
        converted_currency.text = String.format(resources.getString(R.string.converted_amount), currency, convertedAmount.first)
        converted_currency_with_markup.text = String.format(resources.getString(R.string.converted_amount_with_markup), convertedAmount.second)
    }

    override fun showInvalidInputMessage() {

    }
}
