package com.machimbira.currency.api.currency

import com.machimbira.currency.network.resources.currency.ICurrencyResources
import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel

class CurrencyApi(val currencyResources: ICurrencyResources, val repository:IRepository<PersistenceCurrencyModel>): ICurrencyApi {

}