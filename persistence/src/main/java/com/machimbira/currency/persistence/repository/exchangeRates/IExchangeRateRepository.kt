package com.machimbira.currency.persistence.repository.exchangeRates

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceRateModel

interface IExchangeRateRepository: IRepository<PersistenceRateModel> {
    fun getExchangeRateByCode(code: Long): PersistenceRateModel
    fun deleteAll()
}