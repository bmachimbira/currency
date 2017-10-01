package com.machimbira.currency.api.exchangeRate

import com.machimbira.currency.network.resources.exchangeRate.IExchangeRateResources
import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceRateModel

class ExchangeRateApi(val exchangeRateResources: IExchangeRateResources, val exchangePersistenceRateModelRepository: IRepository<PersistenceRateModel>): IExchangeRateApi