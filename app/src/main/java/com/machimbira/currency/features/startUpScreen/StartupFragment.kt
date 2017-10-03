package com.machimbira.currency.features.startUpScreen

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.machimbira.currency.R
import com.machimbira.currency.api.currency.CurrencyApiFactory
import com.machimbira.currency.api.exchangeRate.ExchangeRateApiFactory
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.domain.Currency
import com.machimbira.currency.features.addCurrencyScreen.AddCurrencyActivity
import com.machimbira.currency.features.currencyDetailScreen.CurrencyDetailActivity
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.persistence.repository.currency.CurrencyRepository
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import com.machimbira.currency.service.JobScheduler
import kotlinx.android.synthetic.main.activity_startup.view.*
import kotlinx.android.synthetic.main.content_startup.view.*


class StartupFragment : Fragment(), ICurrencyContract.View, RecyclerViewAdapter.OnItemClickListener {

    companion object {
        fun newInstance(): StartupFragment {
            return StartupFragment()
        }
    }

    private lateinit var rv: RecyclerView
    private lateinit var presenter: ICurrencyContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        JobScheduler.start()

        presenter = CurrencyPresenter(
                currencyApi = CurrencyApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        currencyRepository = CurrencyRepository()),
                ratesApi = ExchangeRateApiFactory.create(
                        retrofit = CurrencyApplication.getClient(),
                        exchangeRateMapper = ExchangeRateMapper(),
                        exchangeRateRepository = ExchangeRateRepository()),
                view = this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.activity_startup, container, false)
        rv = view.my_currencies

        presenter.getMyCurrencies()

        view.fab.setOnClickListener { _ ->
            val addIntent = Intent(activity, AddCurrencyActivity::class.java)
            activity.startActivity(addIntent)
        }
        return view
    }

    override fun showMyCurrencies(myCurrencies: List<Currency>) {
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        val adapter = RecyclerViewAdapter(activity, myCurrencies, this)
        rv.adapter = adapter
    }

    override fun onItemClick(item: Currency) {
        val currencyDetailsIntent = Intent(activity, CurrencyDetailActivity::class.java)
        currencyDetailsIntent.putExtra(CurrencyDetailActivity.CODE, item.code)
        activity.startActivityFromFragment(this, currencyDetailsIntent, 100)
    }
}
