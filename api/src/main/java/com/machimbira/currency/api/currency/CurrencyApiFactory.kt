package com.machimbira.currency.api.currency

import android.content.Context
import com.machimbira.currency.network.NetworkApplication
import com.machimbira.currency.network.resources.currency.CurrencyResourceFactory
import com.machimbira.currency.persistence.repository.CurrencyRepository
import io.realm.Realm

class CurrencyApiFactory{
    companion object {
        fun create(context: Context ): ICurrencyApi {
            Realm.init(context)
            val currencyResources = CurrencyResourceFactory.create(retrofit = NetworkApplication().getClient())
            return CurrencyApi(currencyResources = currencyResources, repository = CurrencyRepository(realm = Realm.getDefaultInstance()))
        }
    }

}