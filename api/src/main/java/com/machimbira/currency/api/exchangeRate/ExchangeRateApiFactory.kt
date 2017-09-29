package com.machimbira.currency.api.exchangeRate

import android.content.Context
import com.machimbira.currency.network.NetworkApplication
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResourceFactory
import com.machimbira.currency.persistence.repository.ExchangeRateRepository
import io.realm.Realm

class ExchangeRateApiFactory {
    companion object {
        fun create(context: Context): IExchangeRateApi{
            Realm.init(context)
            val exchangeRateResources = ExchangeRateResourceFactory.create(retrofit = NetworkApplication().getClient())
            return ExchangeRateApi(exchangeRateResources = exchangeRateResources, exchangeRateRepository = ExchangeRateRepository(realm = Realm.getDefaultInstance()))
        }
    }
}